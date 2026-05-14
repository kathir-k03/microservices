package com.insurance.claim.service;

import com.insurance.claim.client.*;
import com.insurance.claim.dto.*;
import com.insurance.claim.entity.Claim;
import com.insurance.claim.repository.ClaimRepository;
import com.insurance.claim.util.ClaimIdGenerator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimRepository repository;

    private final PolicyClient policyClient;
    private final DocumentClient documentClient;
    private final FraudClient fraudClient;
    private final ApprovalClient approvalClient;
    private final PaymentClient paymentClient;
    private final NotificationClient notificationClient;
    private final CustomerClient customerClient;
    private final AuditClient auditClient;

    public ClaimResponse createClaim(ClaimRequest request) {

        String claimId = ClaimIdGenerator.generateClaimId();

        try {
            // ✅ AUDIT: CLAIM CREATED
            audit("CLAIM_CREATED", claimId, "SUCCESS", "Claim initiated");

            // ✅ STEP 1: POLICY
            if (!validatePolicy(request.getPolicyId())) {
                audit("POLICY_VALIDATION", claimId, "FAILED", "Invalid policy");
                throw new RuntimeException("Invalid policy");
            }
            audit("POLICY_VALIDATION", claimId, "SUCCESS", "Policy validated");

            // ✅ STEP 2: DOCUMENT
            if (!validateDocuments(claimId)) {
                audit("DOCUMENT_VERIFICATION", claimId, "FAILED", "Documents not verified");
                throw new RuntimeException("Documents not verified");
            }
            audit("DOCUMENT_VERIFICATION", claimId, "SUCCESS", "Documents verified");

            // ✅ STEP 3: FRAUD
            FraudResponse fraud = checkFraud(claimId, request.getAmount());
            if (fraud.isFraudulent()) {
                audit("FRAUD_CHECK", claimId, "FAILED", fraud.getReason());
                throw new RuntimeException("Fraud detected: " + fraud.getReason());
            }
            audit("FRAUD_CHECK", claimId, "SUCCESS", "No fraud detected");

            // ✅ STEP 4: APPROVAL
            ApprovalResponse approval = approvalClient.approve(
                    new ApprovalRequest(claimId, request.getAmount())
            );

            audit("APPROVAL", claimId, "SUCCESS", approval.getApprover());

            // ✅ STEP 5: PAYMENT
            PaymentResponse payment = paymentClient.process(
                    new PaymentRequest(claimId, request.getAmount(), "BANK")
            );

            if (!payment.getStatus().equals("SUCCESS")) {
                audit("PAYMENT", claimId, "FAILED", "Payment failed");
                throw new RuntimeException("Payment failed");
            }
            audit("PAYMENT", claimId, "SUCCESS", "Payment completed");

            // ✅ STEP 6: NOTIFICATION
            CustomerResponse customer = customerClient.getCustomer(request.getPolicyId());

            notificationClient.send(new NotificationRequest(
                    claimId,
                    "Claim processed successfully",
                    "EMAIL",
                    customer.getEmail()
            ));

            audit("NOTIFICATION", claimId, "SUCCESS", "Notification sent");

            // ✅ FINAL SAVE
            Claim claim = Claim.builder()
                    .claimId(claimId)
                    .policyId(request.getPolicyId())
                    .amount(request.getAmount())
                    .description(request.getDescription())
                    .status("COMPLETED")
                    .build();

            Claim saved = repository.save(claim);

            return ClaimResponse.builder()
                    .claimId(saved.getClaimId())
                    .status(saved.getStatus())
                    .amount(saved.getAmount())
                    .build();

        } catch (Exception ex) {

            audit("CLAIM_FAILED", claimId, "FAILED", ex.getMessage());
            throw ex;
        }
    }

    // ✅ AUDIT HELPER
    private void audit(String stage, String claimId, String status, String message) {
        auditClient.log(new AuditRequest(claimId, stage, status, message));
    }

    // ✅ RESILIENCE

    @CircuitBreaker(name = "policyService", fallbackMethod = "policyFallback")
    @Retry(name = "policyRetry")
    public boolean validatePolicy(String policyId) {
        return policyClient.validatePolicy(policyId);
    }

    public boolean policyFallback(String policyId, Exception ex) {
        return false;
    }

    @CircuitBreaker(name = "documentService", fallbackMethod = "docFallback")
    @Retry(name = "documentRetry")
    public boolean validateDocuments(String claimId) {
        return documentClient.verifyDocuments(claimId);
    }

    public boolean docFallback(String claimId, Exception ex) {
        return false;
    }

    @CircuitBreaker(name = "fraudService", fallbackMethod = "fraudFallback")
    @Retry(name = "fraudRetry")
    public FraudResponse checkFraud(String claimId, Double amount) {
        return fraudClient.checkFraud(new FraudRequest(claimId, amount));
    }

    public FraudResponse fraudFallback(String claimId, Double amount, Exception ex) {
        FraudResponse fallback = new FraudResponse();
        fallback.setClaimId(claimId);
        fallback.setFraudulent(false);
        fallback.setReason("Fraud service unavailable");
        return fallback;
    }
}

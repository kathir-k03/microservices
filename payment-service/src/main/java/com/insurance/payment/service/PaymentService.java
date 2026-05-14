package com.insurance.payment.service;

import com.insurance.payment.dto.*;
import com.insurance.payment.entity.Payment;
import com.insurance.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentResponse processPayment(PaymentRequest request) {

        // ✅ Idempotency check
        repository.findByClaimId(request.getClaimId())
                .ifPresent(existing -> {
                    throw new RuntimeException("Payment already processed for this claim");
                });

        // ✅ Simulate payment processing
        String transactionId = generateTransactionId();

        Payment payment = Payment.builder()
                .claimId(request.getClaimId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .transactionId(transactionId)
                .status("SUCCESS")
                .remarks("Payment processed successfully")
                .build();

        repository.save(payment);

        return PaymentResponse.builder()
                .claimId(request.getClaimId())
                .status(payment.getStatus())
                .transactionId(transactionId)
                .message("Payment Successful")
                .build();
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
package com.insurance.policy.service;

import com.insurance.policy.dto.*;
import com.insurance.policy.entity.Policy;
import com.insurance.policy.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository repository;

    // ✅ Create Policy
    public PolicyResponse createPolicy(PolicyRequest request) {

        Policy policy = Policy.builder()
                .policyId(request.getPolicyId())
                .customerName(request.getCustomerName())
                .coverageAmount(request.getCoverageAmount())
                .active(true)
                .build();

        Policy saved = repository.save(policy);

        return mapToResponse(saved);
    }

    // ✅ Validate Policy (used by Claim Service)
    public boolean validatePolicy(String policyId) {

        return repository.findByPolicyId(policyId)
                .map(policy -> policy.isActive()
                        && policy.getCoverageAmount() > 0)
                .orElse(false);
    }

    // ✅ Get Policy Details
    public PolicyResponse getPolicy(String policyId) {

        Policy policy = repository.findByPolicyId(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        return mapToResponse(policy);
    }

    // ✅ Activate / Deactivate Policy
    public String updateStatus(String policyId, boolean active) {

        Policy policy = repository.findByPolicyId(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        policy.setActive(active);
        repository.save(policy);

        return "Policy status updated";
    }

    private PolicyResponse mapToResponse(Policy policy) {
        return PolicyResponse.builder()
                .policyId(policy.getPolicyId())
                .customerName(policy.getCustomerName())
                .active(policy.isActive())
                .coverageAmount(policy.getCoverageAmount())
                .build();
    }
}
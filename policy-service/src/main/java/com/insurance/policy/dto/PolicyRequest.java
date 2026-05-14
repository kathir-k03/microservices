package com.insurance.policy.dto;

import lombok.Data;

@Data
public class PolicyRequest {
    private String policyId;
    private String customerName;
    private Double coverageAmount;
}
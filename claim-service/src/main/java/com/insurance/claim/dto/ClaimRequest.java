package com.insurance.claim.dto;

import lombok.Data;

@Data
public class ClaimRequest {
    private String policyId;
    private Double amount;
    private String description;
}

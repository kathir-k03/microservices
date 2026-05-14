package com.insurance.claim.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClaimResponse {
    private String claimId;
    private String status;
    private Double amount;
}
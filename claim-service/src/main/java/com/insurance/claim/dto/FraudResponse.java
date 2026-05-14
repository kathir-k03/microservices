package com.insurance.claim.dto;

import lombok.Data;

@Data
public class FraudResponse {
    private String claimId;
    private boolean fraudulent;
    private String reason;
}
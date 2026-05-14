package com.insurance.fraud.dto;

import lombok.Data;

@Data
public class FraudRequest {
    private String claimId;
    private Double amount;
}

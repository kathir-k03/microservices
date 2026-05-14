package com.insurance.claim.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private String claimId;
    private String status;
    private String transactionId;
    private String message;
}


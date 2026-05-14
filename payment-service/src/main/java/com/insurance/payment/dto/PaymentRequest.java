package com.insurance.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {

    private String claimId;
    private Double amount;
    private String paymentMethod; // UPI / CARD / BANK
}

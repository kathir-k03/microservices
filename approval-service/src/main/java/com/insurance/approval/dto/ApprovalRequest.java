package com.insurance.approval.dto;

import lombok.Data;

@Data
public class ApprovalRequest {
    private String claimId;
    private Double amount;
}

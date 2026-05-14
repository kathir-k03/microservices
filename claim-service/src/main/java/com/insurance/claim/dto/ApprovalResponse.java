package com.insurance.claim.dto;

import lombok.Data;

@Data
public class ApprovalResponse {
    private String claimId;
    private String status;
    private String approver;
}

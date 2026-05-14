package com.insurance.approval.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApprovalResponse {
    private String claimId;
    private String status;
    private String approver;
}
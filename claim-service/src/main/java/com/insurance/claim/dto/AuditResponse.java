package com.insurance.claim.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditResponse {

    private String claimId;
    private String stage;
    private String status;
    private String message;
    private LocalDateTime timestamp;
}

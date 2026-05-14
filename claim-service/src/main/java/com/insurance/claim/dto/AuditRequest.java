package com.insurance.claim.dto;

import lombok.*;

@Data
@AllArgsConstructor   
@NoArgsConstructor
public class AuditRequest {

    private String claimId;
    private String stage;
    private String status;
    private String message;
}
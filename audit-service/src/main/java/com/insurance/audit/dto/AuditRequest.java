package com.insurance.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   
@NoArgsConstructor
public class AuditRequest {

    private String claimId;
    private String stage;
    private String status;
    private String message;
}

package com.insurance.document.dto;

import lombok.Data;

@Data
public class DocumentResponse {
    private String claimId;
    private String fileName;
    private boolean verified;
}


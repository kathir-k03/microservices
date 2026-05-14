package com.insurance.fraud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FraudResponse {
    private String claimId;
    private boolean fraudulent;
    private String reason;
}

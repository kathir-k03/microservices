package com.insurance.fraud.strategy;

import com.insurance.fraud.dto.FraudRequest;
import org.springframework.stereotype.Component;

@Component
public class DuplicateClaimStrategy implements FraudStrategy {

    @Override
    public boolean isFraud(FraudRequest request) {
        return request.getClaimId().endsWith("999"); // sample logic
    }

    @Override
    public String getReason() {
        return "Duplicate claim pattern detected";
    }
}
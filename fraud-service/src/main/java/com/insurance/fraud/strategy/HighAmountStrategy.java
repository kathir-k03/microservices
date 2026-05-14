package com.insurance.fraud.strategy;

import com.insurance.fraud.dto.FraudRequest;
import org.springframework.stereotype.Component;

@Component
public class HighAmountStrategy implements FraudStrategy {

    @Override
    public boolean isFraud(FraudRequest request) {
        return request.getAmount() > 100000;
    }

    @Override
    public String getReason() {
        return "High claim amount detected";
    }
}
package com.insurance.fraud.strategy;

import com.insurance.fraud.dto.FraudRequest;

public interface FraudStrategy {

    boolean isFraud(FraudRequest request);

    String getReason();
}
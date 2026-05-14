package com.insurance.fraud.service;

import com.insurance.fraud.dto.*;
import com.insurance.fraud.entity.FraudCheck;
import com.insurance.fraud.repository.FraudRepository;
import com.insurance.fraud.strategy.FraudStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final List<FraudStrategy> strategies; // ✅ auto-injected
    private final FraudRepository repository;

    public FraudResponse checkFraud(FraudRequest request) {

        for (FraudStrategy strategy : strategies) {

            if (strategy.isFraud(request)) {

                FraudCheck entity = FraudCheck.builder()
                        .claimId(request.getClaimId())
                        .fraudulent(true)
                        .reason(strategy.getReason())
                        .build();

                repository.save(entity);

                return FraudResponse.builder()
                        .claimId(request.getClaimId())
                        .fraudulent(true)
                        .reason(strategy.getReason())
                        .build();
            }
        }

        FraudCheck entity = FraudCheck.builder()
                .claimId(request.getClaimId())
                .fraudulent(false)
                .reason("No fraud detected")
                .build();

        repository.save(entity);

        return FraudResponse.builder()
                .claimId(request.getClaimId())
                .fraudulent(false)
                .reason("No fraud detected")
                .build();
    }
}
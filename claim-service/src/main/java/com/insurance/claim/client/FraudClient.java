package com.insurance.claim.client;

import com.insurance.claim.dto.FraudRequest;
import com.insurance.claim.dto.FraudResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "fraud-service")
public interface FraudClient {

    @PostMapping("/fraud/check")
    FraudResponse checkFraud(@RequestBody FraudRequest request);
}

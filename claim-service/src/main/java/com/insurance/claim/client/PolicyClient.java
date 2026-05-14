package com.insurance.claim.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "policy-service")
public interface PolicyClient {

    @GetMapping("/policies/{policyId}/validate")
    boolean validatePolicy(@PathVariable String policyId);
}
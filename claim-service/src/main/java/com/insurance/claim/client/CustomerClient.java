package com.insurance.claim.client;

import com.insurance.claim.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/customers/{policyId}")
    CustomerResponse getCustomer(@PathVariable("policyId") String policyId);
}
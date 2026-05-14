package com.insurance.claim.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.claim.dto.ApprovalRequest;
import com.insurance.claim.dto.ApprovalResponse;

@FeignClient(name = "approval-service")
public interface ApprovalClient {

    @PostMapping("/approval")
    ApprovalResponse approve(@RequestBody ApprovalRequest request);
}


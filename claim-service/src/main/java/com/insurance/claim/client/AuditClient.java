package com.insurance.claim.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.claim.dto.AuditRequest;

@FeignClient(name = "audit-service")
public interface AuditClient {

    @PostMapping("/audit")
    void log(@RequestBody AuditRequest request);
}

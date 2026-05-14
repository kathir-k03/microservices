package com.insurance.claim.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "document-service")
public interface DocumentClient {

    @GetMapping("/documents/{claimId}/verify")
    boolean verifyDocuments(@PathVariable String claimId);
}

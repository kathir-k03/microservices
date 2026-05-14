package com.insurance.policy.controller;

import com.insurance.policy.dto.*;
import com.insurance.policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policies")
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyService service;

    // ✅ Create policy
    @PostMapping
    public PolicyResponse create(@RequestBody PolicyRequest request) {
        return service.createPolicy(request);
    }

    // ✅ Validate (used by Claim Service)
    @GetMapping("/{policyId}/validate")
    public boolean validate(@PathVariable String policyId) {
        return service.validatePolicy(policyId);
    }

    // ✅ Get details
    @GetMapping("/{policyId}")
    public PolicyResponse get(@PathVariable String policyId) {
        return service.getPolicy(policyId);
    }

    // ✅ Activate / Deactivate
    @PutMapping("/{policyId}/status")
    public String updateStatus(
            @PathVariable String policyId,
            @RequestParam boolean active) {
        return service.updateStatus(policyId, active);
    }
}

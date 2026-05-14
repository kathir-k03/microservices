package com.insurance.audit.controller;

import com.insurance.audit.dto.*;
import com.insurance.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService service;

    // ✅ Log event (internal use)
    @PostMapping
    public void log(@RequestBody AuditRequest request) {
        service.log(request);
    }

    // ✅ Get claim tracking
    @GetMapping("/{claimId}")
    public List<AuditResponse> get(@PathVariable String claimId) {
        return service.getHistory(claimId);
    }
}
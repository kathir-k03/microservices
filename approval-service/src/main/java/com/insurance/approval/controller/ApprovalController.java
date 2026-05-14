package com.insurance.approval.controller;

import com.insurance.approval.dto.*;
import com.insurance.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService service;

    @PostMapping
    public ApprovalResponse approve(@RequestBody ApprovalRequest request) {
        return service.approveClaim(request);
    }
}

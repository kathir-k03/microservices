package com.insurance.claim.controller;

import com.insurance.claim.dto.*;
import com.insurance.claim.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
public class ClaimController {

    private final ClaimService service;

    @PostMapping
    public ClaimResponse create(@RequestBody ClaimRequest request) {
        return service.createClaim(request);
    }
}
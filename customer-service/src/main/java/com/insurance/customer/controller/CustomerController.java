package com.insurance.customer.controller;

import com.insurance.customer.dto.*;
import com.insurance.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request) {
        return service.create(request);
    }

    @GetMapping("/{policyId}")
    public CustomerResponse get(@PathVariable String policyId) {
        return service.getByPolicyId(policyId);
    }
}

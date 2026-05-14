package com.insurance.payment.controller;

import com.insurance.payment.dto.*;
import com.insurance.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        return service.processPayment(request);
    }
}
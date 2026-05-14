package com.insurance.fraud.controller;

import com.insurance.fraud.dto.*;
import com.insurance.fraud.service.FraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
public class FraudController {

    private final FraudService service;

    @PostMapping("/check")
    public FraudResponse check(@RequestBody FraudRequest request) {
        return service.checkFraud(request);
    }
}
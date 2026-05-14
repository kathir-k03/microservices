package com.insurance.notification.controller;

import com.insurance.notification.dto.*;
import com.insurance.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public NotificationResponse send(@RequestBody NotificationRequest request) {
        return service.send(request);
    }
}
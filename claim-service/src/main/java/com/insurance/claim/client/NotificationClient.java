package com.insurance.claim.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.insurance.claim.dto.NotificationRequest;
import com.insurance.claim.dto.NotificationResponse;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/notifications")
    NotificationResponse send(@RequestBody NotificationRequest request);
}

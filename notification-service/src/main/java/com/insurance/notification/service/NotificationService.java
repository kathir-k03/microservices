package com.insurance.notification.service;

import com.insurance.notification.dto.*;
import com.insurance.notification.entity.Notification;
import com.insurance.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationResponse send(NotificationRequest request) {

        // ✅ Simulate sending notification
        System.out.println("Sending " + request.getType() +
                " for Claim: " + request.getClaimId());

        Notification notification = Notification.builder()
                .claimId(request.getClaimId())
                .message(request.getMessage())
                .type(request.getType())
                .status("SENT")
                .build();

        repository.save(notification);

        return NotificationResponse.builder()
                .claimId(request.getClaimId())
                .status("SENT")
                .message("Notification sent successfully")
                .build();
    }
}
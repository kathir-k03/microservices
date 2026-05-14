package com.insurance.notification.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationResponse {

    private String claimId;
    private String status;
    private String message;
}
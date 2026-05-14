package com.insurance.claim.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private String claimId;
    private String message;
    private String type;       // EMAIL / SMS
    private String recipient;  // ✅ email or phone
}

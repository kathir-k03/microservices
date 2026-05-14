package com.insurance.notification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimId;
    private String message;
    private String type; // EMAIL / SMS
    private String status; // SENT / FAILED
}
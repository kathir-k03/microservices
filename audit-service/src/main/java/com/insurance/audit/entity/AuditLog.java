package com.insurance.audit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit_logs", indexes = {
        @Index(name = "idx_claimId", columnList = "claimId")
})
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimId;

    private String stage; // e.g. POLICY_VALIDATED

    private String status; // SUCCESS / FAILED

    private String message;

    private LocalDateTime timestamp;
}

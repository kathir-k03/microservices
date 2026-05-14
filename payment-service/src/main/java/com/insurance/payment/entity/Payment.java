package com.insurance.payment.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "claimId") // ✅ idempotency
})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String claimId;
    private Double amount;

    private String status; // SUCCESS / FAILED

    private String transactionId;

    private String paymentMethod;

    private String remarks;
}
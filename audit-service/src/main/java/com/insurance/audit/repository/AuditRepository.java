package com.insurance.audit.repository;

import com.insurance.audit.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByClaimIdOrderByTimestampAsc(String claimId);
}
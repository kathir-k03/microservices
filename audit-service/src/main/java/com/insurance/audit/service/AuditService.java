package com.insurance.audit.service;

import com.insurance.audit.dto.*;
import com.insurance.audit.entity.AuditLog;
import com.insurance.audit.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository repository;

    // ✅ Save audit event
    public void log(AuditRequest request) {

        AuditLog log = AuditLog.builder()
                .claimId(request.getClaimId())
                .stage(request.getStage())
                .status(request.getStatus())
                .message(request.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        repository.save(log);
    }

    // ✅ Fetch full history
    public List<AuditResponse> getHistory(String claimId) {

        return repository.findByClaimIdOrderByTimestampAsc(claimId)
                .stream()
                .map(log -> AuditResponse.builder()
                        .claimId(log.getClaimId())
                        .stage(log.getStage())
                        .status(log.getStatus())
                        .message(log.getMessage())
                        .timestamp(log.getTimestamp())
                        .build())
                .collect(Collectors.toList());
    }
}
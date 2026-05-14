package com.insurance.approval.service;

import com.insurance.approval.chain.*;
import com.insurance.approval.dto.*;
import com.insurance.approval.entity.Approval;
import com.insurance.approval.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ManagerApprover manager;
    private final DirectorApprover director;
    private final CEOApprover ceo;

    private final ApprovalRepository repository;

    public ApprovalResponse approveClaim(ApprovalRequest request) {

        // ✅ Build chain
        manager.setNext(director);
        director.setNext(ceo);

        // ✅ Execute chain
        ApprovalResult result = manager.approve(
                request.getClaimId(),
                request.getAmount()
        );

        // ✅ Save result
        repository.save(Approval.builder()
                .claimId(request.getClaimId())
                .status(result.getStatus())
                .approver(result.getApprover())
                .build());

        return ApprovalResponse.builder()
                .claimId(request.getClaimId())
                .status(result.getStatus())
                .approver(result.getApprover())
                .build();
    }
}
package com.insurance.approval.chain;

import org.springframework.stereotype.Component;

@Component
public class DirectorApprover extends Approver {

    @Override
    public ApprovalResult approve(String claimId, Double amount) {

        if (amount <= 100000) {
            return new ApprovalResult("APPROVED", "DIRECTOR");
        }
        return next.approve(claimId, amount);
    }
}

package com.insurance.approval.chain;

import org.springframework.stereotype.Component;

@Component
public class ManagerApprover extends Approver {

    @Override
    public ApprovalResult approve(String claimId, Double amount) {

        if (amount <= 50000) {
            return new ApprovalResult("APPROVED", "MANAGER");
        }
        return next.approve(claimId, amount);
    }
}
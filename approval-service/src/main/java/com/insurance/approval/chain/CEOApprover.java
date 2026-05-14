package com.insurance.approval.chain;

import org.springframework.stereotype.Component;

@Component
public class CEOApprover extends Approver {

    @Override
    public ApprovalResult approve(String claimId, Double amount) {
        return new ApprovalResult("APPROVED", "CEO");
    }
}

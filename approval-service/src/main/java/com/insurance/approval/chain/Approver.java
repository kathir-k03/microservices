package com.insurance.approval.chain;

public abstract class Approver {

    protected Approver next;

    public void setNext(Approver next) {
        this.next = next;
    }

    public abstract ApprovalResult approve(String claimId, Double amount);
}

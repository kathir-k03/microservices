package com.insurance.approval.chain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApprovalResult {

    private String status;
    private String approver;
}

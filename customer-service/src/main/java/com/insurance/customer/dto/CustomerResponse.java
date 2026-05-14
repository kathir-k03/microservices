package com.insurance.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String policyId;
    private String name;
    private String email;
    private String phone;
}
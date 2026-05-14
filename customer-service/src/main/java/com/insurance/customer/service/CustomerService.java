package com.insurance.customer.service;
import com.insurance.customer.dto.*;
import com.insurance.customer.entity.Customer;
import com.insurance.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse create(CustomerRequest request) {

        // ✅ Prevent duplicate
        if (repository.existsByPolicyId(request.getPolicyId())) {
            throw new RuntimeException("Customer already exists for this policy");
        }

        Customer customer = Customer.builder()
                .policyId(request.getPolicyId())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();

        Customer saved = repository.save(customer);

        return map(saved);
    }

    public CustomerResponse getByPolicyId(String policyId) {

        Customer customer = repository.findByPolicyId(policyId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return map(customer);
    }

    private CustomerResponse map(Customer c) {
        return CustomerResponse.builder()
                .policyId(c.getPolicyId())
                .name(c.getName())
                .email(c.getEmail())
                .phone(c.getPhone())
                .build();
    }
}


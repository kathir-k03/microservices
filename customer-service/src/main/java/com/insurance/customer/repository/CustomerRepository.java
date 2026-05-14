package com.insurance.customer.repository;

import com.insurance.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPolicyId(String policyId);

    boolean existsByPolicyId(String policyId);
}

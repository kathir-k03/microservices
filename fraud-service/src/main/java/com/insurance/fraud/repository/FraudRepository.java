package com.insurance.fraud.repository;

import com.insurance.fraud.entity.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<FraudCheck, Long> {
}
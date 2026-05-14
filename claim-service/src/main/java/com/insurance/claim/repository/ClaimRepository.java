package com.insurance.claim.repository;

import com.insurance.claim.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Optional<Claim> findByClaimId(String claimId);
}
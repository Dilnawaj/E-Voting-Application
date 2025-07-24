package org.example.repo;

import org.example.entity.ElectionCommission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionCommissionRepository extends JpaRepository<ElectionCommission,Integer> {

Optional<ElectionCommission> findByEmailAddress(String email);
}

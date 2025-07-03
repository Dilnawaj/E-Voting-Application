package org.example.repo;

import org.example.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepo extends JpaRepository<Voter,Integer> {
    Optional<Voter> findByAadharNumber(String aadharNumber);
}

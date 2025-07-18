package org.example.repo;

import org.example.entity.VoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRequestRepo extends JpaRepository<VoteRequest,Integer> {
Optional<VoteRequest> findByCandidateIdAndAadharNumber(Integer candidateId, String aadharNumber);
}

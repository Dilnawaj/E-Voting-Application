package org.example.repo;

import org.example.entity.CandidateVoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateVoteRecordRepo extends JpaRepository<CandidateVoteRecord,Integer> {
Optional<CandidateVoteRecord> findByCandidateId(Integer candidateId);
}

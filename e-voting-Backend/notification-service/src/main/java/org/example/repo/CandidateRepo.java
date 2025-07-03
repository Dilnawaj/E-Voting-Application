package org.example.repo;

import org.example.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

    Optional<Candidate> findByCandidateId(Integer candidateId);

}

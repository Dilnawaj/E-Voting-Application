package org.example.repo;

import org.example.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepo extends JpaRepository<Candidate,Integer> {
    List<Candidate> findByConstituency(String constituency);
    List<Candidate> findByParty(String partyName);
    Optional<Candidate> findByEmailAddress(String emailAddress);
}

package org.example.repo;

import org.example.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoterRepo extends JpaRepository<Voter,Integer> {
    Optional<Voter> findByAadharNumber(String aadharNumber);
    Optional<Voter> findByAadharNumberOrEmailAddress(String aadharNumber,String emailAddress);
    List<Voter> findByConstituency(String constituency);

    Optional<Voter> findByEmailAddress(String emailAddress);
}

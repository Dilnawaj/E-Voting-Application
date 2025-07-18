package org.example.repo;

import org.example.entity.Candidate;
import org.example.model.VoteStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateRepo extends JpaRepository<Candidate,Integer> {
    List<Candidate> findByConstituency(String constituency);
    List<Candidate> findByParty(String partyName);
    Optional<Candidate> findByPartyAndConstituency(String partyName,String constituency);
    Optional<Candidate> findByEmailAddress(String emailAddress);

    @Query("SELECT new org.example.model.VoteStats(c.name, c.party, c.totalVotes) FROM Candidate c WHERE c.constituency = :constituency")
    List<VoteStats> getVoteStats(@Param("constituency") String constituency);

}

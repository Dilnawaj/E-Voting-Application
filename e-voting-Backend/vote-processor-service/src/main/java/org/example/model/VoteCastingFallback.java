package org.example.model;

import org.example.service.VoterCastingClient;
import org.springframework.stereotype.Component;

@Component
public class VoteCastingFallback implements VoterCastingClient {
    @Override
    public void voteDoneUpdateStatus(Integer candidateId, Integer aadharNumber) {
        System.out.println("⚠ Circuit breaker triggered! markAsVoted fallback called for Aadhar: " + aadharNumber+" with Candidate Id "+candidateId);
    }
}
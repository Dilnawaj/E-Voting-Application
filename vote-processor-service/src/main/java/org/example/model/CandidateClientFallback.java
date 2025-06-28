package org.example.model;

import org.example.service.CandidateClient;
import org.springframework.stereotype.Component;

@Component
public class CandidateClientFallback implements CandidateClient {
    @Override
    public void incrementVote(Integer candidateId) {
        System.out.println("Circuit breaker triggered! incrementVote fallback called for candidateId: " + candidateId);

    }
}

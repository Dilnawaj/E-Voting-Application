package org.example.model;

import org.example.service.VoterClient;
import org.springframework.stereotype.Component;

@Component
public class VoterClientFallback implements VoterClient {

    @Override
    public void markAsVoted(String aadharNumber) {
        System.out.println("⚠ Circuit breaker triggered! markAsVoted fallback called for Aadhar: " + aadharNumber);
    }
}
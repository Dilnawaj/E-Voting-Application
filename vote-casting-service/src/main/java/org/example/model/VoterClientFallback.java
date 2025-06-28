package org.example.model;

import org.example.service.VoterClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VoterClientFallback implements VoterClient {



    @Override
    public ResponseEntity<Boolean> isEligibleToVote(String aadharNumber) {
          System.out.println("Circuit breaker triggered! isEligibleToVote fallback called for Aadhar: " + aadharNumber);;

   return ResponseEntity.ok(false);
    }
}
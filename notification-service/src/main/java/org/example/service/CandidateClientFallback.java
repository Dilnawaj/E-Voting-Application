package org.example.service;

import org.example.model.CandidateModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CandidateClientFallback implements CandidateClientService {


    @Override
    public ResponseEntity<CandidateModel> getCandidateBYCandidateId(Integer candidateId) {
        System.out.println("Circuit breaker triggered! getCandidateBYCandidateId fallback called for candidateId: " + candidateId);
return ResponseEntity.ok(new CandidateModel());
    }
}

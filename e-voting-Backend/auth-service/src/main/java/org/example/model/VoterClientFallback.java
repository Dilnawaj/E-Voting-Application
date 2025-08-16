package org.example.model;

import org.example.feign.VoterClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VoterClientFallback implements VoterClient {
    @Override
    public ResponseEntity<String> login(UserModel loginRequestDto) {
        System.out.println("Circuit breaker triggered! login fallback called for voter  " + loginRequestDto.getEmail());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}

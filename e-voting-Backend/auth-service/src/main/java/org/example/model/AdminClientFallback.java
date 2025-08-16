package org.example.model;

import org.example.feign.AdminClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminClientFallback implements AdminClient {
    @Override
    public ResponseEntity<String> login(UserModel loginRequestDto) {
        System.out.println("Circuit breaker triggered! login fallback called for admin  " + loginRequestDto.getEmail());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}

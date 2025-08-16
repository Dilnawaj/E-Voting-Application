package org.example.feign;


import org.example.model.CandidateClientFallback;
import org.example.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "candidate", fallback = CandidateClientFallback.class)
public interface CandidateClient {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserModel loginRequestDto);
}
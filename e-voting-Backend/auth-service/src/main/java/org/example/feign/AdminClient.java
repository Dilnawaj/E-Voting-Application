package org.example.feign;

import org.example.model.AdminClientFallback;
import org.example.model.CandidateClientFallback;
import org.example.model.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "election-commission", fallback = AdminClientFallback.class)
public interface AdminClient {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserModel loginRequestDto);
}
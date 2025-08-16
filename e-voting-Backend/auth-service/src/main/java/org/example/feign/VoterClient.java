package org.example.feign;


import org.example.model.UserModel;
import org.example.model.VoterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "voter", fallback = VoterClientFallback.class)
public interface VoterClient {

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserModel loginRequestDto);

}
package org.example.service;


import org.example.model.VoterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "voter", fallback = VoterClientFallback.class)
public interface VoterClient {

    @GetMapping("/voter")
    ResponseEntity<Boolean> isEligibleToVote(@RequestParam("aadharNumber") String aadharNumber);
}
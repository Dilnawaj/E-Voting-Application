package org.example.service;


import org.example.model.CandidateClientFallback;
import org.example.model.VoterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "candidate", fallback = CandidateClientFallback.class)
public interface CandidateClient {

    @PutMapping("/candidate")
    void incrementVote(@RequestParam("candidateId") Integer candidateId);
}
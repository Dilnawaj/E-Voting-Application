package org.example.service;

import org.example.model.VoteCastingFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vote-casting", fallback = VoteCastingFallback.class)
public interface VoterCastingClient {


    @PutMapping("/voted")
    void voteDoneUpdateStatus(@RequestParam("candidateId") Integer candidateId,@RequestParam("aadharNumber") String aadharNumber);
}

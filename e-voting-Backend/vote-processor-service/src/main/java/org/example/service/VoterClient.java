package org.example.service;


import org.example.model.VoterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "voter", fallback = VoterClientFallback.class)
public interface VoterClient {

    @GetMapping("/{aadharNumber}")
    void markAsVoted(@PathVariable("aadharNumber") String aadharNumber);
}
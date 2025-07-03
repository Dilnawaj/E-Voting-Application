package org.example.service;


import org.example.model.CandidateModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "candidate",fallback = CandidateClientFallback.class)
public interface CandidateClientService {

    @GetMapping("/candidate/{candidateId}")
    ResponseEntity<CandidateModel> getCandidateBYCandidateId(@PathVariable("candidateId") Integer candidateId);
}
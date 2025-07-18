package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.VoteRequestDTO;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("cast")
    public ResponseEntity<String> castVote(@RequestParam Integer candidateId,@RequestParam String aadharNumber) throws JsonProcessingException {
      System.out.println("hello");
        voteService.processVote(candidateId,aadharNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Vote request received and sent for processing.");
    }
    @PutMapping("/voted")
    public ResponseEntity<String> updateVoteStatus(@RequestParam Integer candidateId,@RequestParam String aadharNumber){
        voteService.updateVoteStatus(candidateId,aadharNumber);
        return ResponseEntity.status(HttpStatus.OK).body("Vote status has been successfully updated.");
    }

}

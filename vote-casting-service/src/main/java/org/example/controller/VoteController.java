package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.VoteRequestDTO;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<String> castVote(@RequestBody VoteRequestDTO dto) throws JsonProcessingException {
        voteService.processVote(dto);
        return ResponseEntity.status(HttpStatus.OK).body("Vote request received and sent for processing.");
    }

}

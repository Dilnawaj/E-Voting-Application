package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.LoginRequestDto;
import org.example.model.VoterModel;
import org.example.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;
import java.util.Map;

@RestController
public class VoterController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private VoterService voterService;

    @PostMapping
    ResponseEntity<VoterModel> addVoter(@RequestBody VoterModel voterModel) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(voterService.addVoter(voterModel));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        System.out.println("Voter Login");
        return  ResponseEntity.status(HttpStatus.OK).body(voterService.login(loginRequestDto));
    }

    @GetMapping("list")
    ResponseEntity<List<VoterModel>> allVoter() {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getAllVoter());
    }

    @GetMapping("/voterId/{voterId}")
    ResponseEntity<VoterModel> getVoterById(@PathVariable Integer voterId) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getVoterById(voterId));
    }
    @GetMapping
    ResponseEntity<Boolean> getVoterById(@RequestParam String aadharNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.isEligibleToVote(aadharNumber));
    }
    @GetMapping("/{aadharNumber}")
    ResponseEntity<VoterModel> getVoterByAadharCard(@PathVariable String aadharNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.markAsVoted(aadharNumber));
    }

    @DeleteMapping
    ResponseEntity<String> deleteVoterById(@RequestParam Integer voterId) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.deleteVoterById(voterId));
    }
    @GetMapping("/ping")
    public String ping() {
        return "Responding from VoterService on port: " + port;
    }


    @GetMapping("voter-insights")
    ResponseEntity<Map<String,Long>> getVoterInsight(@RequestParam String constituency)
    {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getVoterInsight(constituency));
    }


    @GetMapping("/voter-data")
    ResponseEntity<VoterModel> getVoterByEmailAddress(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getVoterByEmail(email));
    }
}

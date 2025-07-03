package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.CandidateModel;
import org.example.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
@PostMapping
    ResponseEntity<CandidateModel> addCandidate(@RequestBody CandidateModel candidateModel) throws JsonProcessingException {
    return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.addCandidate(candidateModel));
}

@PutMapping
    ResponseEntity<CandidateModel> updateCandidate(@RequestParam Integer candidateId)
{
    return ResponseEntity.status(HttpStatus.OK).body(candidateService.updateCandidateVote(candidateId));
}


@GetMapping("{candidateId}")
ResponseEntity<CandidateModel> getCandidateBYCandidateId(@PathVariable Integer candidateId)
{
return ResponseEntity.status(HttpStatus.OK).body(candidateService.getCandidateById(candidateId));
}


@GetMapping
    ResponseEntity<List<CandidateModel>> getAllCandidates(@RequestParam String partyName)
{
    return  ResponseEntity.status(HttpStatus.OK).body(candidateService.allCandidatesOfParty(partyName));
}

@DeleteMapping
    ResponseEntity<String> deleteCandidate(@RequestParam Integer candidateId){
    return ResponseEntity.status(HttpStatus.OK).body(candidateService.deleteByCandidateId(candidateId));
}


@GetMapping("all")
ResponseEntity<List<CandidateModel>> deleteCandidate(){
    return ResponseEntity.status(HttpStatus.OK).body(candidateService.getAllCandidates());
}
}

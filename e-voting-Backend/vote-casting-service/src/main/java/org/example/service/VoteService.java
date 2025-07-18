package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.VoteRequest;
import org.example.exception.CustomException;
import org.example.model.VoteRequestDTO;
import org.example.repo.VoteRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {
    @Autowired
    private VoterClient voterClient;

@Autowired
private VoteRequestRepo voteRequestRepo;

@Autowired
VoteRequestProducer voteRequestProducer;
    public void processVote(VoteRequestDTO dto) throws JsonProcessingException {
        System.out.println("AAAAAAAAAAAAdhar Number "+dto.getAadharNumber());
    ResponseEntity<Boolean> response= voterClient.isEligibleToVote(dto.getAadharNumber());

System.out.println(response.getBody());
        if(Boolean.FALSE.equals(response.getBody()))
        {
            throw  new CustomException(HttpStatus.BAD_REQUEST.value(),"Voter is not eligible." );

        }
        addDataInVoteRequest(dto);

    }

    private void addDataInVoteRequest(VoteRequestDTO dto) throws JsonProcessingException {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setAadharNumber(dto.getAadharNumber());
        voteRequest.setCandidateId(dto.getCandidateId());
        voteRequest.setStatus("PENDING");
        voteRequest.setRequestAt(LocalDateTime.now());
        voteRequestRepo.save(voteRequest);
        voteRequestProducer.sendVoterRegisteredEvent(dto);
        voteRequest.setStatus("SENT_TO_KAFKA");
        voteRequestRepo.save(voteRequest);
    }

    public void updateVoteStatus(Integer candidateId, String aadharNumber) {
   Optional<VoteRequest> voterOpt =voteRequestRepo.findByCandidateIdAndAadharNumber(candidateId,aadharNumber);
   if(voterOpt.isPresent())
   {
       voterOpt.get().setStatus("VOTE_DONE");
       voteRequestRepo.save(voterOpt.get());

   }

    }
}

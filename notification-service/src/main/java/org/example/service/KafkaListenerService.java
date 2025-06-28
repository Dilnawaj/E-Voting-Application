package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Candidate;
import org.example.entity.Voter;
import org.example.model.CandidateModel;
import org.example.model.VoterCandidateModel;
import org.example.model.VoterModel;
import org.example.repo.CandidateRepo;
import org.example.repo.VoterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaListenerService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VoterRepo voterRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CandidateRepo candidateRepo;



    @RetryableTopic
    @KafkaListener(topics = "${registration.topic}")
    public void registrationVoteEvent(String message) {
        try {
            System.out.println(" Voter message is "+message);
            VoterModel voterKafka = objectMapper.readValue(message, VoterModel.class);
Voter voter= this.modelMapper.map(voterKafka,Voter.class);
            voterRepo.save(voter);
            emailService.sendEmail(voter.getEmailAddress(),voter.getName());
            System.out.println(" Notification for Registerred Voter");
        } catch (Exception e) {
            System.out.println("Failed to process vote event: " + e.getMessage());
        }
    }
    @RetryableTopic
    @KafkaListener(topics = "${processor.topic}")
    public void voteEvent(String message) {
        try {
            System.out.println(" Voter  Processed message is "+message);
            VoterCandidateModel voterKafka = objectMapper.readValue(message, VoterCandidateModel.class);

      Optional<Voter> voterOpt=      voterRepo.findByAadharNumber(voterKafka.getAadharNumber());
        Optional<Candidate> candidateOpt = candidateRepo.findByCandidateId(voterKafka.getCandidateId());

         if(voterOpt.isPresent() && candidateOpt.isPresent())
         {
        Voter voter=     voterOpt.get();
         Candidate candidate=    candidateOpt.get();
             emailService.sendEmailForVoteDoneVoter(voter.getEmailAddress(),voter.getEmailAddress(),candidate.getParty(),candidate.getName());
             System.out.println(" Notification for Done  Voting");
         }


        } catch (Exception e) {
            System.out.println("Failed to process vote event: " + e.getMessage());
        }
    }
    @KafkaListener(topics = "${candidate.topic}")
    public void registrationCandidateEvent(String message) {
        try {
            System.out.println(" Voter message is "+message);
            CandidateModel candidateKafka = objectMapper.readValue(message, CandidateModel.class);
            Candidate candidate= this.modelMapper.map(candidateKafka,Candidate.class);
            candidateRepo.save(candidate);
            emailService.sendEmailToCandidate(candidate.getEmailAddress(),candidate.getName(),candidate.getParty());
            System.out.println(" Notification for Registerred Voter");
        } catch (Exception e) {
            System.out.println("Failed to process vote event: " + e.getMessage());
        }
    }

}

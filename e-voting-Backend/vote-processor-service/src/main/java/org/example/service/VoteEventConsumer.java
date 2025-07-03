package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.MessageProducer;
import org.example.model.VoteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

@Service
public class VoteEventConsumer {

    @Value("${processor.topic}")
    private String topicName;
    @Autowired
    private CandidateClient candidateClient;

    @Autowired
    private VoterClient voterClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageProducer messageProducer;

    @RetryableTopic
    @KafkaListener(topics = "${cast.topic}")
    public void consumeVote(String  message) throws JsonProcessingException {
        System.out.println("CAST message is "+message);
        VoteRequestDTO voteRequest = objectMapper.readValue(message, VoteRequestDTO.class);
        System.out.println("Received vote for candidateId: " + voteRequest.getCandidateId());

        candidateClient.incrementVote(voteRequest.getCandidateId());
        System.out.println("candidate DOne");
        voterClient.markAsVoted(voteRequest.getAadharNumber());
        System.out.println("voter  DOne");

        messageProducer.sendMessage(topicName,objectMapper.writeValueAsString(voteRequest));
    }











}

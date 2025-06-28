package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.MessageProducer;
import org.example.entity.Voter;
import org.example.model.VoterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VoterKafkaProducer {

    @Value("${vote.topic}")
    private String topicName;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageProducer messageProducer;

    public void sendVoterRegisteredEvent(Voter voter) throws JsonProcessingException {
        VoterEvent event = new VoterEvent();
        event.setVoterId(voter.getVoterId());
        event.setAge(voter.getAge());
        event.setName(voter.getName());
        event.setAadharNumber(voter.getAadharNumber());
        event.setEmailAddress(voter.getEmailAddress());
        messageProducer.sendMessage(topicName, objectMapper.writeValueAsString(event));
    }
}

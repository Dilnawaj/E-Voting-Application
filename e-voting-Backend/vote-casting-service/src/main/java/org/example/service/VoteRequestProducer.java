package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.MessageProducer;
import org.example.model.VoteRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VoteRequestProducer {


    @Value("${cast.topic}")
    private String topicName;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageProducer messageProducer;

    public void sendVoterRegisteredEvent(VoteRequestDTO voteRequestDTO) throws JsonProcessingException {

        messageProducer.sendMessage(topicName, objectMapper.writeValueAsString(voteRequestDTO));
    }
}

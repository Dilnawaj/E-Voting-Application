package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.CandidateModel;
import org.example.model.VoteRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CandidateClientService candidateClient;

    @Autowired
    private ResultService resultService;

    @RetryableTopic
    @KafkaListener(topics = "${vote.topic}")
    public void consumeVoteEvent(String message) {
        try {
            System.out.println("Processor message is "+message);
            VoteRequestDTO voteRequest = objectMapper.readValue(message, VoteRequestDTO.class);
            System.out.println("Received vote for candidateId: " + voteRequest.getCandidateId());

            ResponseEntity<CandidateModel>  candidateOpt=candidateClient.getCandidateBYCandidateId(voteRequest.getCandidateId());
            resultService.incrementVote(voteRequest.getCandidateId(),candidateOpt.getBody().getName(),candidateOpt.getBody().getParty());
        } catch (Exception e) {
            System.out.println("Failed to process vote event: " + e.getMessage());
        }
    }

    /**
     * The `dltHandler` function logs an error message with the provided booking message.
     *
     * @param bookingMessage The `bookingMessage` parameter in the `dltHandler` method is a string that
     *                       contains information related to a booking error.
     */
//    @DltHandler
//    public void dltHandler(String bookingMessage) {
//        try {
//            // removing error key from redis as room is still available
//            Booking booking = objectMapper.readValue(bookingMessage, Booking.class);
//            String redisKeys = ApplicationConstants.ROOM_AVAILABILITY + booking.getRoom().getId();
//            redisTemplate.delete(redisKeys);
//        } catch (JsonProcessingException e) {
//            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
//        }
//        log.info("error with this message : {}", bookingMessage);
//    }
}

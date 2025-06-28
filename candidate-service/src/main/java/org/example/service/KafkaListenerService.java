package org.example.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateService candidateService;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    /**
     * This Java function listens to a Kafka topic for booking messages, deserializes them into a
     * Booking object, and saves them to a repository with retry functionality.
     *
     * @param bookingMessage The `bookingMessage` parameter in the `bookingConsumer` method is the
     *                       message received from the Kafka topic "booking-topic". This message is expected to be in a JSON
     *                       format representing a booking, which is then deserialized into a `Booking` object using the
     *                       `objectMapper.readValue` method.
     */
    @RetryableTopic
    @KafkaListener(topics = "${vote.topic}")
    public void consumeVoteEvent(String candidateIdStr) {
        try {
            Integer candidateId =Integer.parseInt(candidateIdStr);
            candidateService.updateCandidateVote(candidateId);
            System.out.println(" Vote received and counted for candidate ID: " + candidateId);
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

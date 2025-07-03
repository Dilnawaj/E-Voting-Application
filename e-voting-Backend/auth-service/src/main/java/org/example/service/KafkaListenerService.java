package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @RetryableTopic
    @KafkaListener(topics = "${user.topic}")
    public void consumeVoteEvent(String registerEvent) {
        try {
            UserDTO user = objectMapper.readValue(registerEvent, UserDTO.class);

            userService.registerUser(user);
            System.out.println(" User Succesfully created: " + user.getEmail());
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

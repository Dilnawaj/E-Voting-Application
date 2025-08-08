package org.example.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserDTO;
import org.example.config.MessageProducer;
import org.example.entity.ElectionCommission;
import org.example.entity.Event;
import org.example.model.ElectionCommissionModel;
import org.example.model.EventModel;
import org.example.model.EventStatus;
import org.example.repo.ElectionCommissionRepository;
import org.example.repo.EventRepo;
import org.example.service.ElectionCommissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ElectionCommissionServiceImpl implements ElectionCommissionService {
    @Autowired
    private ElectionCommissionRepository electionCommissionRepo;
    @Autowired
    private MessageProducer messageProducer;
    @Value("${user.topic}")
    private String userTopic;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EventRepo eventRepo;

    @Override
    public  Map<String,String> addAdmin(ElectionCommissionModel electionCommissionModel) throws JsonProcessingException {

        ElectionCommission electionCommission=this.modelMapper.map(electionCommissionModel,ElectionCommission.class);
          electionCommissionRepo.save(electionCommission);
        UserDTO userModel = new UserDTO(electionCommission.getEmailAddress(),electionCommission.getPassword(),"ADMIN");
        messageProducer.sendMessage(userTopic,objectMapper.writeValueAsString(userModel));

        Map<String,String> response = new HashMap<>();
        response.put("message","Admin added successfully.");
        return response;
    }

    @Override
    public ElectionCommission getAdminDetailsByAdmin(String email) {
        Optional<ElectionCommission> electionCommissionOpt = electionCommissionRepo.findByEmailAddress(email);
        return electionCommissionOpt.orElseGet(ElectionCommission::new);
    }

    @Override
    public List<EventModel> getEventDetails() {

        return eventRepo.getAllEvents();
    }
    @Override
    public List<EventModel> getEventDetailsByEmail(String email) {
        Optional<ElectionCommission> electionCommissionOptional=  electionCommissionRepo.findByEmailAddress(email);
        return  electionCommissionOptional.get().getEvent().stream().map(e->this.modelMapper.map(e,EventModel.class)).collect(Collectors.toList());


    }


    @Override
    public EventModel addEventDetails(EventModel eventModel,String email) {
        System.out.println("Start Date "+eventModel.getStartDate());
        Optional<ElectionCommission> electionCommissionOptional=  electionCommissionRepo.findByEmailAddress(email);
        if(electionCommissionOptional.isEmpty())
        {
            throw new RuntimeException("Admin not found");
        }
      Event event=  this.modelMapper.map(eventModel, Event.class);
        if(getMillisecondsFromLocalDate(eventModel.getStartDate())>new Date().getTime())
        {
            event.setEventStatus(EventStatus.Upcoming);
        }
        else {
            event.setEventStatus(EventStatus.Ongoing);
        }

        event.setElectionCommission(electionCommissionOptional.get());
      return this.modelMapper.map(eventRepo.save(event),EventModel.class);
    }


    public static long getMillisecondsFromLocalDate(LocalDate date) {
        LocalDateTime localDateTime = date.atStartOfDay(); // 00:00 time
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant().toEpochMilli();
    }

}

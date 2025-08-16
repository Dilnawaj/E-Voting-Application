package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.LoginRequestDto;
import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;
import org.example.model.EventModel;

import java.util.List;
import java.util.Map;

public interface ElectionCommissionService {
    Map<String,String> addAdmin(ElectionCommissionModel electionCommission) throws JsonProcessingException;

    ElectionCommission getAdminDetailsByAdmin(String email);

    List<EventModel> getEventDetails();

    EventModel addEventDetails(EventModel eventModel,String email);

    List<EventModel> getEventDetailsByEmail(String email);

    String login(LoginRequestDto loginRequestDto);
}

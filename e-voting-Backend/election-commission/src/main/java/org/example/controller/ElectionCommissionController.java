package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;
import org.example.model.EventModel;
import org.example.service.ElectionCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ElectionCommissionController {


    @Autowired
    private ElectionCommissionService electionCommissionService;

    @PostMapping
    ResponseEntity<Map<String,String>> addAdminInElectionService(@RequestBody ElectionCommissionModel electionCommission) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.addAdmin(electionCommission));
    }
    @GetMapping
    ResponseEntity<ElectionCommission> getAdminDetailsByEmail(@RequestParam String email)
    {
        System.out.println("Hello");
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.getAdminDetailsByAdmin(email));
    }

    @GetMapping("events")
    ResponseEntity<List<EventModel>> getEventDetails()
    {
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.getEventDetails());
    }
    @GetMapping("events/{email}")
    ResponseEntity<List<EventModel>> getEventDetails(@PathVariable String email)
    {
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.getEventDetailsByEmail(email));
    }
    @PostMapping("events")
    ResponseEntity<EventModel> addEventDetails(@RequestBody EventModel eventModel,@RequestParam String email)
    {
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.addEventDetails(eventModel,email));
    }


}

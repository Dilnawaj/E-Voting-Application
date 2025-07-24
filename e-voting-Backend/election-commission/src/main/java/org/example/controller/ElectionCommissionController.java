package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;
import org.example.service.ElectionCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.getAdminDetailsByAdmin(email));
    }


}

package org.example.controller;

import org.example.model.ElectionCommissionModel;
import org.example.service.ElectionCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ElectionCommissionController {


    @Autowired
    private ElectionCommissionService electionCommissionService;

    @PostMapping
    ResponseEntity<Map<String,String>> addAdminInElectionService(@RequestBody ElectionCommissionModel electionCommission)
    {
        return ResponseEntity.status(HttpStatus.OK).body(electionCommissionService.addAdmin(electionCommission));
    }


}

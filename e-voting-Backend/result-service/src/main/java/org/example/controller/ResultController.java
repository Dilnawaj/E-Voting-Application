package org.example.controller;

import org.example.entity.CandidateVoteRecord;
import org.example.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultController {

    @Autowired
  private  ResultService resultService;


    @GetMapping
    public List<CandidateVoteRecord> getResult()
    {
        return resultService.getAllResult();
    }





}

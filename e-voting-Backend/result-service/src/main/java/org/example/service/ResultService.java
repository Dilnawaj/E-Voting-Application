package org.example.service;

import org.example.entity.CandidateVoteRecord;
import org.example.repo.CandidateVoteRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {


    @Autowired
    private CandidateVoteRecordRepo candidateVoteRecordRepo;


    public void incrementVote(Integer candidateId,String name,String party)
    {

        CandidateVoteRecord record = candidateVoteRecordRepo.findByCandidateId(candidateId).orElse(new CandidateVoteRecord());
        record.setCandidateId(candidateId);
        record.setCandidateName(name);
        record.setParty(party);
        record.setVoteCount(record.getVoteCount()!=null?record.getVoteCount()+1:1);
        candidateVoteRecordRepo.save(record);

    }

    public List<CandidateVoteRecord> getAllResult()
    {
        return candidateVoteRecordRepo.findAll();
    }






}

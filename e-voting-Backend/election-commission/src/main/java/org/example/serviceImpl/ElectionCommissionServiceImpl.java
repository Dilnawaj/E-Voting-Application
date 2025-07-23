package org.example.serviceImpl;

import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;
import org.example.repo.ElectionCommissionRepository;
import org.example.service.ElectionCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ElectionCommissionServiceImpl implements ElectionCommissionService {
    @Autowired
    private ElectionCommissionRepository electionCommissionRepo;
    @Override
    public  Map<String,String> addAdmin(ElectionCommissionModel electionCommissionModel) {
        ElectionCommission electionCommission = new ElectionCommission();
        electionCommission.setName(electionCommissionModel.getName());
        electionCommission.setPassword(electionCommissionModel.getPassword());
        electionCommission.setEmailAddress(electionCommissionModel.getEmailAddress());
        electionCommissionRepo.save(electionCommission);
        Map<String,String> response = new HashMap<>();
        response.put("message","Admin added successfully.");
        return response;
    }
}

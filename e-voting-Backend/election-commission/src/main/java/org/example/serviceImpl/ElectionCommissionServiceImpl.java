package org.example.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.UserDTO;
import org.example.config.MessageProducer;
import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;
import org.example.repo.ElectionCommissionRepository;
import org.example.service.ElectionCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Override
    public  Map<String,String> addAdmin(ElectionCommissionModel electionCommissionModel) throws JsonProcessingException {
        ElectionCommission electionCommission = new ElectionCommission();
        electionCommission.setName(electionCommissionModel.getName());
        electionCommission.setPassword(electionCommissionModel.getPassword());
        electionCommission.setEmailAddress(electionCommissionModel.getEmailAddress());
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
}

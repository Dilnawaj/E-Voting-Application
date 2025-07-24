package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.ElectionCommission;
import org.example.model.ElectionCommissionModel;

import java.util.Map;

public interface ElectionCommissionService {
    Map<String,String> addAdmin(ElectionCommissionModel electionCommission) throws JsonProcessingException;

    ElectionCommission getAdminDetailsByAdmin(String email);
}

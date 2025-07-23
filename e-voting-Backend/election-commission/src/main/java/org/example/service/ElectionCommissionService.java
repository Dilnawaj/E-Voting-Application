package org.example.service;

import org.example.model.ElectionCommissionModel;

import java.util.Map;

public interface ElectionCommissionService {
    Map<String,String> addAdmin(ElectionCommissionModel electionCommission);
}

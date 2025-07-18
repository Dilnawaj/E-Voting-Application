package org.example.model;

import lombok.Data;

@Data
public class VoteRequestDTO {
    private Integer candidateId;
    private String aadharNumber;

    public VoteRequestDTO(Integer candidateId, String aadharNumber) {
        this.candidateId = candidateId;
        this.aadharNumber = aadharNumber;
    }
}

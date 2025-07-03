package org.example.model;

import lombok.Data;

@Data
public class VoteRequestDTO {

    private String aadharNumber;

    private Integer candidateId;

    private String candidateName;

    private String partyName;
}

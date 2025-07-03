package org.example.model;

import lombok.Data;

@Data
public class CandidateModel {
    private Integer candidateId;

    private String name;

    private String party;

    private Integer totalVotes;

    private String constituency;


}

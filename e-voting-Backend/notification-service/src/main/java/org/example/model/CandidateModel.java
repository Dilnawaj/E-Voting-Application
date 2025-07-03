package org.example.model;

import lombok.Data;

@Data
public class CandidateModel {
    private Integer id;

    private Integer candidateId;

    private String name;

    private String party;


    private String emailAddress;
}

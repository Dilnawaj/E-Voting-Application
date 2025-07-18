package org.example.model;

import lombok.Data;

@Data
public class CandidateModel {
    private Integer candidateId;


    private String banner;


    private String name;

    private String party;

    private Integer totalVotes;

    private String constituency;

    private String emailAddress;

    private String password;

    private String state;
}

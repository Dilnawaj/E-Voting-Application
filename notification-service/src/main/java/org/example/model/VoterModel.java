package org.example.model;

import lombok.Data;

@Data
public class VoterModel {

    private Integer voterId;
    private String emailAddress;
    private String aadharNumber;
    private String name;
}

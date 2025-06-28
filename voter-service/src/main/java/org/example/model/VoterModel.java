package org.example.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VoterModel {

    private String name;

    private String aadharNumber;


    private String emailAddress;

    private Integer age;

    private String constituency;

    private boolean hasVoted;

    private LocalDateTime registeredAt;



}

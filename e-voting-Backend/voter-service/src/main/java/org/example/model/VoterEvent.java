package org.example.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VoterEvent {

    private Integer voterId;

    private String name;


    private String aadharNumber;

    private Integer age;

    private String emailAddress;



}

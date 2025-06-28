package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voterId;

   private String name;

@Column(unique = true)
private String aadharNumber;

private Integer age;

private String constituency;

    private String emailAddress;

private Boolean hasVoted=false;

private LocalDateTime registeredAt;


}

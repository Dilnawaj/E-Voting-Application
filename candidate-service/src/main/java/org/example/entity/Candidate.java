package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer candidateId;

    private String name;

    private String party;

    private Integer totalVotes;

    private String constituency;

    private String emailAddress;
}

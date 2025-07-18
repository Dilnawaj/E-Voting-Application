package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    private String password;

    private String state;
    @OneToMany(mappedBy="candidate" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Notification> notifications = new ArrayList<>();
}

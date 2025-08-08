package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.Data;

import java.util.*;

@Data
@Entity
public class ElectionCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    private String name;

    private String emailAddress;

    private  String password;

    private String about;
    @OneToMany(mappedBy = "electionCommission",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Event> event = new ArrayList<>();

}

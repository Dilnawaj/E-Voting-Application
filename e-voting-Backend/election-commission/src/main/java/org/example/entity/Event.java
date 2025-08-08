package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.example.model.EventStatus;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String constituency;

    private LocalDate startDate;

    private LocalDate endDate;

    private String message;

@Enumerated(EnumType.STRING)
 private EventStatus eventStatus;

    private Double participation;

    @ManyToOne
    @JoinColumn(name="electionCommissionId")
    @JsonBackReference
    private ElectionCommission electionCommission;



}

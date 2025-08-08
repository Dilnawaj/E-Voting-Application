package org.example.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;
import java.time.LocalDate;

public class EventModel {
    private Integer id;

    private String name;

    private String constituency;

    private LocalDate startDate;

    private LocalDate endDate;

    private String message;

    private EventStatus eventStatus;

    private Double participation;
    public EventModel() {

    }
    public EventModel(Integer id) {
        this.id = id;
    }

    public EventModel(Integer id, String name, String constituency, LocalDate startDate, LocalDate endDate, EventStatus eventStatus, Double participation, String message) {
        this.id = id;
        this.name = name;
        this.constituency = constituency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventStatus=eventStatus;
        this.participation = participation;
        this.message=message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Double getParticipation() {
        return participation;
    }

    public void setParticipation(Double participation) {
        this.participation = participation;
    }
}

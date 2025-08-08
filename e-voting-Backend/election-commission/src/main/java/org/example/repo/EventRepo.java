package org.example.repo;

import org.example.entity.Event;
import org.example.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event,Integer> {

@Query("select new org.example.model.EventModel(e.id, e.name, e.constituency,e.startDate, e.endDate,e.eventStatus, e.participation,e.message) from Event e")
    public List<EventModel> getAllEvents();

}

package org.example.repo;

import org.example.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepo  extends JpaRepository<Notification,Integer> {
}

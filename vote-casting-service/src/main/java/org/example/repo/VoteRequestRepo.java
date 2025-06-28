package org.example.repo;

import org.example.entity.VoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRequestRepo extends JpaRepository<VoteRequest,Integer> {
}

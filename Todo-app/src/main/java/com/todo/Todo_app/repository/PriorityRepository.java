package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Priorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriorityRepository extends JpaRepository<Priorities, UUID> {
    Optional<Priorities> findByPriorityName(String priorityName);
}

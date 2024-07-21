package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<Status, UUID> {
    boolean findBystatusName(String statusName);
}

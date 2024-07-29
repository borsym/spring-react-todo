package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, UUID> {
    boolean findBystatusName(String statusName);
}

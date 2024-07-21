package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    boolean findBystatusName(String statusName);
}

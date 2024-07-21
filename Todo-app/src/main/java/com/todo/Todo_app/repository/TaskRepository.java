package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, UUID> {
}
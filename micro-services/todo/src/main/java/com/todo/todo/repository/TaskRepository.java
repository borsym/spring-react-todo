package com.todo.todo.repository;

import com.todo.todo.model.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TasksEntity, UUID> {
}
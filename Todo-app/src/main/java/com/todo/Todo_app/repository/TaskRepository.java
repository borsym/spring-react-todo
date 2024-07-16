package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {
}
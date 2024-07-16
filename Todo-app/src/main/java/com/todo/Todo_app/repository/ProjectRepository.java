package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
}
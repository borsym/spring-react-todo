package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, UUID> {
    Optional<Projects> findByName(String projectName);
}
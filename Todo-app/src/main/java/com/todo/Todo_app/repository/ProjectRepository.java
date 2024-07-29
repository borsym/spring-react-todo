package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.ProjectsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectsEntity, UUID> {
    Optional<ProjectsEntity> findByName(String projectName);
}
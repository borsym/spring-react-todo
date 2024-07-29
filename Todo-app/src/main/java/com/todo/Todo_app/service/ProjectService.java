package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.model.ProjectsEntity;
import com.todo.Todo_app.model.UsersEntity;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectsEntity createProject(ProjectDTO projectsDTO, UsersEntity user);
    List<ProjectsEntity> getAllProjects();
    void deleteProject(UUID id);
}

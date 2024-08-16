package com.todo.todo.service;

import com.todo.todo.dto.ProjectDTO;
import com.todo.todo.model.ProjectsEntity;
import com.todo.todo.model.UsersEntity;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ProjectsEntity createProject(ProjectDTO projectsDTO, UsersEntity user);
    List<ProjectsEntity> getAllProjects();
    void deleteProject(UUID id);
}

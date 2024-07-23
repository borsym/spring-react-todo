package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.model.Projects;
import com.todo.Todo_app.model.Users;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    Projects createProject(ProjectDTO projectsDTO, Users user);
    List<Projects> getAllProjects();
    void deleteProject(UUID id);
}

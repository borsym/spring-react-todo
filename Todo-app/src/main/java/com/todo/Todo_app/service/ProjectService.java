package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.model.Projects;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.ProjectRepository;
import com.todo.Todo_app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Projects createProject(ProjectDTO projectsDTO, Users user) {
        var projectName = projectsDTO.getName();
        if (projectRepository.findByName(projectName).isPresent()) {
            throw new RuntimeException("Project already exists");
        }
        Projects project = new Projects();
        project.setName(projectName);
        project.setDescription(projectsDTO.getDescription());
        project.setCreatedBy(user);
        return projectRepository.save(project);
    }

    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(UUID id) {
        Projects projects = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role doesn't exists"));
        projectRepository.delete(projects);
    }
}

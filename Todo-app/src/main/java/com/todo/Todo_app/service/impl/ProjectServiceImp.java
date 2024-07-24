package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.exception.ProjectNotFoundException;
import com.todo.Todo_app.model.Projects;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.ProjectRepository;
import com.todo.Todo_app.service.ProjectService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImp(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    @Override
    public Projects createProject(ProjectDTO projectsDTO, Users user) {
        var projectName = projectsDTO.getName();
        if (projectRepository.findByName(projectName).isPresent()) {
            throw new RuntimeException("Project already exists");
        }
        Projects project = Projects.builder()
                .name(projectName)
                .description(projectsDTO.getDescription())
                .createdBy(user).build();

        return projectRepository.save(project);
    }
    @Override
    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }
    @Override
    public void deleteProject(UUID id) throws ProjectNotFoundException {
        Projects projects = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
        projectRepository.delete(projects);
    }
}

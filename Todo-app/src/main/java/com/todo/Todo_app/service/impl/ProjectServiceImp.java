package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.exception.ProjectNotFoundException;
import com.todo.Todo_app.model.ProjectsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.ProjectRepository;
import com.todo.Todo_app.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public ProjectsEntity createProject(ProjectDTO projectsDTO, UsersEntity user) {
        var projectName = projectsDTO.getName();
        if (projectRepository.findByName(projectName).isPresent()) {
            throw new RuntimeException("Project already exists");
        }
        ProjectsEntity project = ProjectsEntity.builder()
                .name(projectName)
                .description(projectsDTO.getDescription())
                .createdBy(user).build();

        return projectRepository.save(project);
    }
    @Override
    public List<ProjectsEntity> getAllProjects() {
        return projectRepository.findAll();
    }
    @Override
    public void deleteProject(UUID id) throws ProjectNotFoundException {
        ProjectsEntity projects = projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
        projectRepository.delete(projects);
    }
}

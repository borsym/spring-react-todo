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

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Projects createProject(ProjectDTO projectsDTO, Users user) {
//        if (projectRepository.findByName(projectsDTO.getProjectName())) {
//            throw new RuntimeException("Project already exists");
//        }
        Projects project = new Projects();
        project.setName(projectsDTO.getProjectName());
        project.setDescription(projectsDTO.getDescription());
        project.setCreatedBy(user);
        return projectRepository.save(project);
    }

    public List<Projects> getAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        Projects projects = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role doesn't exists"));
        projectRepository.delete(projects);
    }
}

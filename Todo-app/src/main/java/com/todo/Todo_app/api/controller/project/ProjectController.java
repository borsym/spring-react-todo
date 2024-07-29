package com.todo.Todo_app.api.controller.project;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.model.ProjectsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.ProjectServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {
    private final ProjectServiceImp projectService;

    public ProjectController(ProjectServiceImp projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectsEntity>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal UsersEntity user) {
        try {
            ProjectsEntity projects = projectService.createProject(projectDTO, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(projects);
        } catch (Exception ex) {
            log.error("Error occurred while creating project with details: {}", projectDTO, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable UUID id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            log.error("Error occurred while deleting project for ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

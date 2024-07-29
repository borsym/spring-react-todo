package com.todo.Todo_app.api.controller.project;

import com.todo.Todo_app.dto.ProjectDTO;
import com.todo.Todo_app.model.ProjectsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.ProjectServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceImp projectService;

    @GetMapping
    public ResponseEntity<List<ProjectsEntity>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<ProjectsEntity> createProject(@RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal UsersEntity user) {
        ProjectsEntity projects = projectService.createProject(projectDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(projects);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

}

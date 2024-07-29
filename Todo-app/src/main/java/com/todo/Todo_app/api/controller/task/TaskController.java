package com.todo.Todo_app.api.controller.task;


import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.TasksEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.TaskServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImp taskService;

    @GetMapping
    public List<TasksEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasksEntity> getTaskById(@PathVariable UUID id) {
        return taskService.getTaskById(id).map(task -> {
            log.info("Task with ID {} retrieved successfully", id);
            return ResponseEntity.ok(task);
        }).orElseGet(() -> {
            log.warn("Task with ID {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping
    public ResponseEntity<TasksEntity> createTask(@Valid @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UsersEntity user) {
        TasksEntity newTask = taskService.createTask(taskDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TasksEntity> updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
        TasksEntity updateTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updateTask);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content

    }

}

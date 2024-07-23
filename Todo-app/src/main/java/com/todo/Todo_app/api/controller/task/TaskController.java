package com.todo.Todo_app.api.controller.task;


import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.service.impl.TaskServiceImp;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskServiceImp taskService;

    public TaskController(TaskServiceImp taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable UUID id) {
        return taskService.getTaskById(id).map(task -> {
            log.info("Task with ID {} retrieved successfully", id);
            return ResponseEntity.ok(task);
        }).orElseGet(() -> {
            log.warn("Task with ID {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal Users user) {
        try {
            Tasks newTask = taskService.createTask(taskDTO, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
        } catch (Exception ex) {
            log.error("Error occurred while creating task with details: {}", taskDTO, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the task");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
        try {
            Tasks updateTask = taskService.updateTask(id, taskDTO);
            return ResponseEntity.ok(updateTask);
        } catch (Exception ex) {
            log.error("Error occurred while updating task for ID: {} with details: {}", id, taskDTO, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the task");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            log.error("Error occurred while deleting task for ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

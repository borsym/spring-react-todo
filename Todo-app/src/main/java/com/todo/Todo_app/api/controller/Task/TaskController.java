package com.todo.Todo_app.api.controller.Task;


import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        try {
            Tasks newTask = taskService.createTask(taskDTO);
            return ResponseEntity.ok(newTask);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the task");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        try {
            Tasks updateTask = taskService.updateTask(id, taskDTO);
            return ResponseEntity.ok(updateTask);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the task");
        }

    }

}

package com.todo.Todo_app.api.controller.Task;


import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

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
    public ResponseEntity<Tasks> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        Tasks newTask = taskService.addTask(taskDTO);
        return ResponseEntity.ok(newTask);
    }

}

package com.todo.Todo_app.api.controller.priority;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.model.Priorities;
import com.todo.Todo_app.service.PriorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/priorities")
public class PriorityController {
    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    public ResponseEntity<List<Priorities>> getAllPriorites() {
        return ResponseEntity.ok(priorityService.getAllPriority());
    }

    @PostMapping
    public ResponseEntity<?> createPriority(@RequestBody PriorityDTO priorityDTO) {
        try {
            return ResponseEntity.ok(priorityService.createPriority(priorityDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable UUID id) {
        try {
            priorityService.deletePriority(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

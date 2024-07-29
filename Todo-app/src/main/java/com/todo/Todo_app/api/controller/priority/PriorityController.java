package com.todo.Todo_app.api.controller.priority;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.model.PrioritiesEntity;
import com.todo.Todo_app.service.impl.PriorityServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/priorities")
@Slf4j
public class PriorityController {
    private final PriorityServiceImp priorityService;

    public PriorityController(PriorityServiceImp priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping
    public ResponseEntity<List<PrioritiesEntity>> getAllPriorities() {
        return ResponseEntity.ok(priorityService.getAllPriority());
    }

    @PostMapping
    public ResponseEntity<?> createPriority(@RequestBody PriorityDTO priorityDTO) {
        try {
            PrioritiesEntity priorities = priorityService.createPriority(priorityDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(priorities);
        } catch (Exception ex) {
            log.error("Error occurred while creating priority with details: {}", priorityDTO, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable UUID id) {
        try {
            priorityService.deletePriority(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            log.error("Error occurred while deleting priority for ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

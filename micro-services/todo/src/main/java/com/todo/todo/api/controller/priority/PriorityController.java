package com.todo.todo.api.controller.priority;

import com.todo.todo.dto.PriorityDTO;
import com.todo.todo.model.PrioritiesEntity;
import com.todo.todo.service.impl.PriorityServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/priorities")
@Slf4j
@RequiredArgsConstructor
public class PriorityController {
    private final PriorityServiceImp priorityService;

    @GetMapping
    public ResponseEntity<List<PrioritiesEntity>> getAllPriorities() {
        return ResponseEntity.ok(priorityService.getAllPriority());
    }

    @PostMapping
    public ResponseEntity<PrioritiesEntity> createPriority(@RequestBody PriorityDTO priorityDTO) {
        PrioritiesEntity priorities = priorityService.createPriority(priorityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(priorities);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriority(@PathVariable UUID id) {
        priorityService.deletePriority(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

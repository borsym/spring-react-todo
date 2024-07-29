package com.todo.Todo_app.api.controller.status;

import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.model.StatusEntity;
import com.todo.Todo_app.service.impl.StatusServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/status")
@Slf4j
@RequiredArgsConstructor
public class StatusController {
    private final StatusServiceImp statusService;

    @GetMapping
    public ResponseEntity<List<StatusEntity>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @PostMapping
    public ResponseEntity<StatusEntity> createStatus(@RequestBody StatusDTO statusDTO) {
        StatusEntity status = statusService.createStatus(statusDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable UUID id) {
        statusService.deleteStatus(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

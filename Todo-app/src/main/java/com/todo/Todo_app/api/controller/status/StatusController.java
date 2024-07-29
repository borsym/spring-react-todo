package com.todo.Todo_app.api.controller.status;

import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.model.StatusEntity;
import com.todo.Todo_app.service.impl.StatusServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/status")
@Slf4j
public class StatusController {
    private final StatusServiceImp statusService;

    public StatusController(StatusServiceImp statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<StatusEntity>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody StatusDTO statusDTO) {
        try {
            StatusEntity status = statusService.createStatus(statusDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(status);
        } catch (Exception ex) {
            log.error("Error occurred while creating status with details: {}", statusDTO, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable UUID id) {
        try {
            statusService.deleteStatus(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            log.error("Error occurred while deleting status for ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

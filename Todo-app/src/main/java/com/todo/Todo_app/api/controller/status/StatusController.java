package com.todo.Todo_app.api.controller.status;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.model.Status;
import com.todo.Todo_app.service.RoleService;
import com.todo.Todo_app.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @PostMapping
    public ResponseEntity<?> createStatus(@RequestBody StatusDTO statusDTO) {
        try {
            return ResponseEntity.ok(statusService.createStatus(statusDTO));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable UUID id) {
        try {
            statusService.deleteStatus(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

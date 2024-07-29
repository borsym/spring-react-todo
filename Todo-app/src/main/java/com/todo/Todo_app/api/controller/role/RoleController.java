package com.todo.Todo_app.api.controller.role;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.model.RolesEntity;
import com.todo.Todo_app.service.impl.RoleServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
@Slf4j
public class RoleController {
    private final RoleServiceImp roleService;

    public RoleController(RoleServiceImp roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RolesEntity>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RolesDTO rolesDTO) {
        try {
            RolesEntity roles = roleService.createRole(rolesDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(roles);
        } catch (Exception ex) {
            log.error("Error occurred while creating role with details: {}", rolesDTO, ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable UUID id) {
        try {
            roleService.deleteRole(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            log.error("Error occurred while deleting role for ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

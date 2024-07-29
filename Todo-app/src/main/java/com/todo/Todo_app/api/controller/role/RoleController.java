package com.todo.Todo_app.api.controller.role;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.model.RolesEntity;
import com.todo.Todo_app.service.impl.RoleServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleController {
    private final RoleServiceImp roleService;

    @GetMapping
    public ResponseEntity<List<RolesEntity>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping
    public ResponseEntity<RolesEntity> createRole(@RequestBody RolesDTO rolesDTO) {
        RolesEntity roles = roleService.createRole(rolesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(roles);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable UUID id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

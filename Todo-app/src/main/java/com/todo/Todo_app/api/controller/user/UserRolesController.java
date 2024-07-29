package com.todo.Todo_app.api.controller.user;

import com.todo.Todo_app.model.UserRolesEntity;
import com.todo.Todo_app.service.impl.UserRoleServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserRolesController {

    private final UserRoleServiceImp userRoleService;

    public UserRolesController(UserRoleServiceImp userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllUsersRoles() {
        try {
            List<UserRolesEntity> userRolesList = userRoleService.getAllUsersRoles();
            return ResponseEntity.ok(userRolesList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> addRoleToUser(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "roleId") UUID roleId) {
        try {
            UserRolesEntity userRole = userRoleService.addRoleToUser(userId, roleId);
            return ResponseEntity.ok(userRole);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> deleteRoleToUser(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "roleId") UUID roleId) {
        try {
            userRoleService.deleteRoleToUser(userId, roleId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

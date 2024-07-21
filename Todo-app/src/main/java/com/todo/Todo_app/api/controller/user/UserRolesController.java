package com.todo.Todo_app.api.controller.user;

import com.todo.Todo_app.model.UserRoles;
import com.todo.Todo_app.service.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRolesController {

    private UserRoleService userRoleService;

    public UserRolesController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getAllUsersRoles() {
        try {
            List<UserRoles> userRolesList = userRoleService.getAllUsersRoles();
            return ResponseEntity.ok(userRolesList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> addRoleToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        try {
            UserRoles userRole = userRoleService.addRoleToUser(userId, roleId);
            return ResponseEntity.ok(userRole);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<?> deleteRoleToUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "roleId") Long roleId) {
        try {
            userRoleService.deleteRoleToUser(userId, roleId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

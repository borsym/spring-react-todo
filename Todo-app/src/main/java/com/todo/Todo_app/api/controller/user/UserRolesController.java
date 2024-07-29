package com.todo.Todo_app.api.controller.user;

import com.todo.Todo_app.model.UserRolesEntity;
import com.todo.Todo_app.service.impl.UserRoleServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRolesController {

    private final UserRoleServiceImp userRoleService;

    @GetMapping("/roles")
    public ResponseEntity<List<UserRolesEntity>> getAllUsersRoles() {
        List<UserRolesEntity> userRolesList = userRoleService.getAllUsersRoles();
        return ResponseEntity.ok(userRolesList);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserRolesEntity> addRoleToUser(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "roleId") UUID roleId) {
        UserRolesEntity userRole = userRoleService.addRoleToUser(userId, roleId);
        return ResponseEntity.ok(userRole);

    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<Void> deleteRoleToUser(@PathVariable(value = "userId") UUID userId, @PathVariable(value = "roleId") UUID roleId) {
        userRoleService.deleteRoleToUser(userId, roleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

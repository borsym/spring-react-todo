package com.todo.todo.api.controller.user;

import com.todo.todo.model.UsersEntity;
import com.todo.todo.service.impl.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userService;

    @GetMapping
    public List<UsersEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable UUID id) {
        Optional<UsersEntity> user = userService.getUserById(id);
        return user
                .map(ResponseEntity::ok) // value present ResponseEntity.ok(user.get())
                .orElseGet(() -> ResponseEntity.notFound().build()); // if not present notFound
    }


}

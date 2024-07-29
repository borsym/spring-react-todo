package com.todo.Todo_app.api.controller.auth;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.LoginResponse;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.UserServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final UserServiceImp userService;

    public AuthController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationBody) {
        log.info("Register request received for email: {}", registrationBody.getEmail());
        try {
            UsersEntity user = userService.registerUser(registrationBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception ex) {
            log.error("Error registering user with email: {}", registrationBody.getEmail(), ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDTO loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public UsersEntity getLoggedInUserProfile(@AuthenticationPrincipal UsersEntity user) {

        log.info("Fetching profile for user with email: {}", user.getEmail());

        return user;
    }
}

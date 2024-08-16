package com.todo.todo.api.controller.auth;

import com.todo.todo.dto.LoginDTO;
import com.todo.todo.dto.LoginResponse;
import com.todo.todo.dto.RegistrationDTO;
import com.todo.todo.model.UsersEntity;
import com.todo.todo.service.impl.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImp userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationBody) {
        log.info("Register request received for email: {}", registrationBody.getEmail());
        UsersEntity user = userService.registerUser(registrationBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
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

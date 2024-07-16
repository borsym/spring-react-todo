package com.todo.Todo_app.api.controller.auth;

import com.todo.Todo_app.api.controller.model.LoginBody;
import com.todo.Todo_app.api.controller.model.LoginResponse;
import com.todo.Todo_app.api.controller.model.RegistrationBody;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginBody loginBody) {
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public Users getLoggedInUserProfile(@AuthenticationPrincipal Users user) {
        return user;
    }
}

package com.todo.Todo_app.service;

import com.todo.Todo_app.model.Users;

public interface JWTService {
    String generateJWT(Users user);

    String getUsername(String token);

}

package com.todo.Todo_app.service;

import com.todo.Todo_app.model.UsersEntity;

public interface JWTService {
    String generateJWT(UsersEntity user);

    String getUsername(String token);

}

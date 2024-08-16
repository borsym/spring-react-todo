package com.todo.todo.service;

import com.todo.todo.model.UsersEntity;

public interface JWTService {
    String generateJWT(UsersEntity user);

    String getUsername(String token);

}

package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.model.UsersEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UsersEntity registerUser(RegistrationDTO registrationBody);
    String loginUser(LoginDTO loginBody);
    List<UsersEntity> getAllUsers();
    Optional<UsersEntity> getUserById(UUID id);



}

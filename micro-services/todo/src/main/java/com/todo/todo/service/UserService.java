package com.todo.todo.service;

import com.todo.todo.dto.LoginDTO;
import com.todo.todo.dto.RegistrationDTO;
import com.todo.todo.model.UsersEntity;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UsersEntity registerUser(RegistrationDTO registrationBody);
    String loginUser(LoginDTO loginBody);
    List<UsersEntity> getAllUsers();
    Optional<UsersEntity> getUserById(UUID id);



}

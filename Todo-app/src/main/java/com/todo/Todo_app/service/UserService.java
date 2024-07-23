package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.Users;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Users registerUser(RegistrationDTO registrationBody) throws UserAlreadyExistsException;
    String loginUser(LoginDTO loginBody);
    List<Users> getAllUsers();
    Optional<Users> getUserById(UUID id);



}

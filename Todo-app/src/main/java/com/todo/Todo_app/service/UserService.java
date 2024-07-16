package com.todo.Todo_app.service;

import com.todo.Todo_app.api.controller.model.RegistrationBody;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.User;
import com.todo.Todo_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(registrationBody.getEmail()).isPresent()
                || userRepository.findByUsername(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        // hash
        user.setPassword(registrationBody.getPassword());

        return userRepository.save(user);
    }
}

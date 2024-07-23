package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final EncryptionServiceImp encryptionService;
    private final JWTServiceImp jwtService;


    public UserServiceImp(UserRepository userRepository, EncryptionServiceImp encryptionService, JWTServiceImp jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    @Override
    public Users registerUser(RegistrationDTO registrationBody) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(registrationBody.getEmail()).isPresent() || userRepository.findByUsername(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Users user = Users.builder().email(registrationBody.getEmail()).username(registrationBody.getUsername()).password(encryptionService.encryptPassword(registrationBody.getPassword())).build();

        return userRepository.save(user);
    }
    @Override
    public String loginUser(LoginDTO loginBody) {
        Optional<Users> optUser = userRepository.findByUsername(loginBody.getUsername());
        if (optUser.isEmpty()) return null;

        Users user = optUser.get();
        if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
            return jwtService.generateJWT(user);
        }

        return null;
    }
    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<Users> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}

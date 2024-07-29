package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.LoginDTO;
import com.todo.Todo_app.dto.RegistrationDTO;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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
    public UsersEntity registerUser(RegistrationDTO registrationBody) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(registrationBody.getEmail()).isPresent() || userRepository.findByUsername(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UsersEntity user = UsersEntity.builder().email(registrationBody.getEmail()).username(registrationBody.getUsername()).password(encryptionService.encryptPassword(registrationBody.getPassword())).build();

        return userRepository.save(user);
    }
    @Override
    public String loginUser(LoginDTO loginBody) {
        Optional<UsersEntity> optUser = userRepository.findByUsername(loginBody.getUsername());
        if (optUser.isEmpty()) return null;

        UsersEntity user = optUser.get();
        if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
            return jwtService.generateJWT(user);
        }

        return null;
    }
    @Override
    public List<UsersEntity> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<UsersEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }
}

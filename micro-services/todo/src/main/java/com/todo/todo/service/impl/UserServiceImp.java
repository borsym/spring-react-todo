package com.todo.todo.service.impl;

import com.todo.todo.api.exception.EntityAlreadyExistsException;
import com.todo.todo.dto.LoginDTO;
import com.todo.todo.dto.RegistrationDTO;
import com.todo.todo.model.UsersEntity;
import com.todo.todo.repository.UserRepository;
import com.todo.todo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final EncryptionServiceImp encryptionService;
    private final JWTServiceImp jwtService;


    @Override
    public UsersEntity registerUser(RegistrationDTO registrationBody) {
        userRepository.findByEmail(registrationBody.getEmail()).ifPresent(user -> {
            throw new EntityAlreadyExistsException("User");
        });

        userRepository.findByUsername(registrationBody.getUsername()).ifPresent(user -> {
            throw new EntityAlreadyExistsException("User");
        });

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

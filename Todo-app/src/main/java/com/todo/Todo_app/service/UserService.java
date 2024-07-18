package com.todo.Todo_app.service;

import com.todo.Todo_app.api.controller.model.LoginBody;
import com.todo.Todo_app.api.controller.model.RegistrationBody;
import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.exception.UserAlreadyExistsException;
import com.todo.Todo_app.model.Projects;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private EncryptionService encryptionService;
    private JWTService jwtService;


    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }


    public Users registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(registrationBody.getEmail()).isPresent()
                || userRepository.findByUsername(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Users user = new Users();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return userRepository.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<Users> optUser = userRepository.findByUsername(loginBody.getUsername());
        if (optUser.isEmpty()) return null;

        Users user = optUser.get();
        if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
            return jwtService.generateJWT(user);
        }

        return null;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }
}

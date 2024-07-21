package com.todo.Todo_app.service;

import com.todo.Todo_app.model.*;
import com.todo.Todo_app.repository.RoleRepository;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class UserRoleService {
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserRoles addRoleToUser(Long userId, Long roleId) {
        Users user = findOrThrow(userRepository, userId, "Users");
        Roles role = findOrThrow(roleRepository, roleId, "Roles");
        if(userRoleRepository.existsByRoleAndUser(role,user)) {
            throw new RuntimeException("User with role exists");
        }
        UserRoles userRole = new UserRoles();
        userRole.setRole(role);
        userRole.setUser(user);

        return  userRoleRepository.save(userRole);
    }

    public List<UserRoles> getAllUsersRoles() {
        return userRoleRepository.findAll();
    }

    public void deleteRoleToUser(Long userId, Long roleId) {
        Roles role = findOrThrow(roleRepository,roleId, "Roles");
        Users user = findOrThrow(userRepository, userId, "Users");
        UserRoles userRoles = userRoleRepository.findByRoleAndUser(role, user)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
        System.out.println(userRoles);
        userRoleRepository.delete(userRoles);
    }
}

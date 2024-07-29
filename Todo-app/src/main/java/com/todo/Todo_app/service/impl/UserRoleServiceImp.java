package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.api.exception.EntityNotFoundException;
import com.todo.Todo_app.model.RolesEntity;
import com.todo.Todo_app.model.UserRolesEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.RoleRepository;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.repository.UserRoleRepository;
import com.todo.Todo_app.service.UserRoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRolesEntity addRoleToUser(UUID userId, UUID roleId) {
        UsersEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        RolesEntity role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role"));

        if (userRoleRepository.existsByRoleAndUser(role, user)) {
            throw new RuntimeException("User with role exists");
        }
        UserRolesEntity userRole = UserRolesEntity.builder().role(role).user(user).build();

        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRolesEntity> getAllUsersRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public void deleteRoleToUser(UUID userId, UUID roleId)  {
        UsersEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        RolesEntity role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role"));
        UserRolesEntity userRoles = userRoleRepository.findByRoleAndUser(role, user)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        System.out.println(userRoles);
        userRoleRepository.delete(userRoles);
    }
}

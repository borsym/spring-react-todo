package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.exception.RoleNotFoundException;
import com.todo.Todo_app.model.*;
import com.todo.Todo_app.repository.RoleRepository;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.repository.UserRoleRepository;
import com.todo.Todo_app.service.UserRoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserRolesEntity addRoleToUser(UUID userId, UUID roleId) {
        UsersEntity user = findOrThrow(userRepository, userId, "Users");
        RolesEntity role = findOrThrow(roleRepository, roleId, "Roles");
        if(userRoleRepository.existsByRoleAndUser(role,user)) {
            throw new RuntimeException("User with role exists");
        }
        UserRolesEntity userRole = UserRolesEntity.builder().role(role).user(user).build();

        return  userRoleRepository.save(userRole);
    }
    @Override
    public List<UserRolesEntity> getAllUsersRoles() {
        return userRoleRepository.findAll();
    }
    @Override
    public void deleteRoleToUser(UUID userId, UUID roleId) throws RoleNotFoundException {
        RolesEntity role = findOrThrow(roleRepository,roleId, "Roles");
        UsersEntity user = findOrThrow(userRepository, userId, "Users");
        UserRolesEntity userRoles = userRoleRepository.findByRoleAndUser(role, user)
                .orElseThrow(RoleNotFoundException::new);
        System.out.println(userRoles);
        userRoleRepository.delete(userRoles);
    }
}

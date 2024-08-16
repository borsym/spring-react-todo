package com.todo.todo.service;

import com.todo.todo.model.UserRolesEntity;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    void deleteRoleToUser(UUID userId, UUID roleId);
    List<UserRolesEntity> getAllUsersRoles();
    UserRolesEntity addRoleToUser(UUID userId, UUID roleId);

}

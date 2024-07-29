package com.todo.Todo_app.service;

import com.todo.Todo_app.exception.RoleNotFoundException;
import com.todo.Todo_app.model.UserRolesEntity;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    void deleteRoleToUser(UUID userId, UUID roleId) throws RoleNotFoundException;
    List<UserRolesEntity> getAllUsersRoles();
    UserRolesEntity addRoleToUser(UUID userId, UUID roleId);

}

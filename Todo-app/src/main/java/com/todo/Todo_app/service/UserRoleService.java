package com.todo.Todo_app.service;

import com.todo.Todo_app.model.UserRoles;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    void deleteRoleToUser(UUID userId, UUID roleId);
    List<UserRoles> getAllUsersRoles();
    UserRoles addRoleToUser(UUID userId, UUID roleId);

}

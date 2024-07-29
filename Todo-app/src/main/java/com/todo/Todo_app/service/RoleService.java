package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.exception.RoleNotFoundException;
import com.todo.Todo_app.model.RolesEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RolesEntity createRole(RolesDTO rolesDTO);
    List<RolesEntity> getAllRoles();
    void deleteRole(UUID id) throws RoleNotFoundException;
}

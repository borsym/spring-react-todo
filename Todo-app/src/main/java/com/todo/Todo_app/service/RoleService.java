package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.exception.RoleNotFoundException;
import com.todo.Todo_app.model.Roles;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Roles createRole(RolesDTO rolesDTO);
    List<Roles> getAllRoles();
    void deleteRole(UUID id) throws RoleNotFoundException;
}

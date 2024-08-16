package com.todo.todo.service;

import com.todo.todo.dto.RolesDTO;
import com.todo.todo.model.RolesEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RolesEntity createRole(RolesDTO rolesDTO);
    List<RolesEntity> getAllRoles();
    void deleteRole(UUID id);
}

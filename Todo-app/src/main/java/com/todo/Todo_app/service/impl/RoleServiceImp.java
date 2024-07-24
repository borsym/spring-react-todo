package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.exception.RoleNotFoundException;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.repository.RoleRepository;
import com.todo.Todo_app.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Roles createRole(RolesDTO rolesDTO) {
        var roleName = rolesDTO.getRoleName();
        if (roleRepository.findByroleName(roleName)) {
            throw new RuntimeException("Role already exists");
        }
        Roles role = Roles.builder().roleName(roleName).build();
        return roleRepository.save(role);
    }
    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public void deleteRole(UUID id) throws RoleNotFoundException {
        Roles role = roleRepository.findById(id)
                .orElseThrow(RoleNotFoundException::new);
        roleRepository.delete(role);
    }
}

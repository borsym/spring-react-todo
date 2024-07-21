package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Roles createRole(RolesDTO rolesDTO) {
        var roleName = rolesDTO.getRoleName();
        if (roleRepository.findByroleName(roleName)) {
            throw new RuntimeException("Role already exists");
        }
        Roles role = new Roles();
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        Roles role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role doesn't exists"));
        roleRepository.delete(role);
    }
}

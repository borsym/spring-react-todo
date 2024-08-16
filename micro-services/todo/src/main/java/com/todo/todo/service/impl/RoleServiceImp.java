package com.todo.todo.service.impl;

import com.todo.todo.api.exception.EntityNotFoundException;
import com.todo.todo.dto.RolesDTO;
import com.todo.todo.model.RolesEntity;
import com.todo.todo.repository.RoleRepository;
import com.todo.todo.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RolesEntity createRole(RolesDTO rolesDTO) {
        val roleName = rolesDTO.getRoleName();
        if (roleRepository.findByroleName(roleName)) {
            throw new RuntimeException("Role already exists");
        }
        RolesEntity role = RolesEntity.builder().roleName(roleName).build();
        return roleRepository.save(role);
    }
    @Override
    public List<RolesEntity> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public void deleteRole(UUID id)  {
        RolesEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role"));
        roleRepository.delete(role);
    }
}

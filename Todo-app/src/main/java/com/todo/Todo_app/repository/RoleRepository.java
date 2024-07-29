package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RolesEntity, UUID> {
    boolean findByroleName(String roleName);
}

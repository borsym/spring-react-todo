package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    boolean findByroleName(String roleName);
}

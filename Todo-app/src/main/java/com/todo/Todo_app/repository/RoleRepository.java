package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, Long> {
}

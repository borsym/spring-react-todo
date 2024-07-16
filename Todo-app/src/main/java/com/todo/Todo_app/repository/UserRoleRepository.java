package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoles, Long> {
}

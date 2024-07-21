package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, UUID> {
    Optional<UserRoles> findByRoleAndUser(Roles role, Users user);
    boolean existsByRoleAndUser(Roles role, Users user);
}

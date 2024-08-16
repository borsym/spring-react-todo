package com.todo.todo.repository;

import com.todo.todo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRolesEntity, UUID> {
    Optional<UserRolesEntity> findByRoleAndUser(RolesEntity role, UsersEntity user);
    boolean existsByRoleAndUser(RolesEntity role, UsersEntity user);
}

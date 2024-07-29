package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByUsername(String name);
    Optional<UsersEntity> findByEmail(String email);
}


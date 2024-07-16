package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String name);
    Optional<Users> findByEmail(String email);
}

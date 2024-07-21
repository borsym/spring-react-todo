package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Teams, UUID> {
}

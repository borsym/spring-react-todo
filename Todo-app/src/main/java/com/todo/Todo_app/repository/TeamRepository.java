package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Teams, Long> {
}

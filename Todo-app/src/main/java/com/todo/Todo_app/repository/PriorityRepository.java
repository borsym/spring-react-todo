package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Priorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priorities, Long> {
}

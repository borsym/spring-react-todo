package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, UUID> {
    List<Comments> findByTask(Tasks task);
}

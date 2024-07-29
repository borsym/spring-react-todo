package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.CommentsEntity;
import com.todo.Todo_app.model.TasksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentsEntity, UUID> {
    List<CommentsEntity> findByTask(TasksEntity task);
}

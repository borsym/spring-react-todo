package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByTask(Tasks task);
}

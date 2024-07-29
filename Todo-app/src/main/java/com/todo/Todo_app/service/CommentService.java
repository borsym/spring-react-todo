package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.CommentsEntity;
import com.todo.Todo_app.model.UsersEntity;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentsEntity createComment(UUID id, CommentDTO commentDTO, UsersEntity user);
    List<CommentsEntity> getCommentsByTaskId(UUID id);
    void deleteComment(UUID id);

}

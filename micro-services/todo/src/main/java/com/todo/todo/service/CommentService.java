package com.todo.todo.service;

import com.todo.todo.dto.CommentDTO;
import com.todo.todo.model.CommentsEntity;
import com.todo.todo.model.UsersEntity;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentsEntity createComment(UUID id, CommentDTO commentDTO, UsersEntity user);
    List<CommentsEntity> getCommentsByTaskId(UUID id);
    void deleteComment(UUID id);

}

package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Users;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    Comments createComment(UUID id, CommentDTO commentDTO, Users user);
    List<Comments> getCommentsByTaskId(UUID id);
    void deleteComment(UUID id);

}

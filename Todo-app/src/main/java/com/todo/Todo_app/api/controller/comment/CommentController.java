package com.todo.Todo_app.api.controller.comment;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.CommentsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.CommentServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImp commentService;

    @PostMapping("/tasks/{id}")
    public ResponseEntity<CommentsEntity> addComment(@PathVariable UUID id, @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal UsersEntity user) {
        CommentsEntity comment = commentService.createComment(id, commentDTO, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<List<CommentsEntity>> getCommentsByTaskId(@PathVariable UUID id) {
        List<CommentsEntity> comments = commentService.getCommentsByTaskId(id);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
}

package com.todo.Todo_app.api.controller.comment;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.CommentsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.CommentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    private final CommentServiceImp commentService;

    public CommentController(CommentServiceImp commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<?> addComment(@PathVariable UUID id, @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal UsersEntity user) {
        try{
            CommentsEntity comment = commentService.createComment(id, commentDTO, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } catch (Exception ex) {
            log.error("Error adding comment for task ID: {} by user: {}", id, user.getEmail(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getCommentsByTaskId(@PathVariable UUID id) {
        try {
            List<CommentsEntity> comments = commentService.getCommentsByTaskId(id);
            return ResponseEntity.ok(comments);
        }catch(Exception ex) {
            log.error("Error retrieving comments for task ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            log.error("Error deleting comment for comment ID: {}", id, ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

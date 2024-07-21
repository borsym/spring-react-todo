package com.todo.Todo_app.api.controller.comment;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<?> addComment(@PathVariable UUID id, @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal Users user) {
        try{
            Comments comment = commentService.createComment(id, commentDTO, user);
            return ResponseEntity.ok(comment);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getCommentsByTaskId(@PathVariable UUID id) {
        try {
            List<Comments> comments = commentService.getCommentsByTaskId(id);
            return ResponseEntity.ok(comments); // TODO change this to created
        }catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}

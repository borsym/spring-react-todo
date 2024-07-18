package com.todo.Todo_app.api.controller.comment;

import com.todo.Todo_app.dto.CommentDTO;
import com.todo.Todo_app.model.Comments;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<Comments> addComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO, @AuthenticationPrincipal Users user) {
        Comments comment = commentService.createComment(id, commentDTO, user);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<List<Comments>> getCommentsByTaskId(@PathVariable Long id) {
        List<Comments> comments = commentService.getCommentsByTaskId(id);
        return ResponseEntity.ok(comments);
    }
}

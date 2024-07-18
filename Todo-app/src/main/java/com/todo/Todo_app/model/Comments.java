package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

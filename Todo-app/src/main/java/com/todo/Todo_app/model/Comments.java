package com.todo.Todo_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private Tasks task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

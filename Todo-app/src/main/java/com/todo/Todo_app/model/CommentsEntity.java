package com.todo.Todo_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference
    private TasksEntity task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

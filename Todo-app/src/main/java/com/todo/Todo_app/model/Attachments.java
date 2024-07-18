package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Attachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    //TODO uuid
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Users uploadedBy;

    private LocalDateTime createdAt;

}

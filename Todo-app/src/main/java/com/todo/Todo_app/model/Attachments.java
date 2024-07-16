package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Attachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasks task;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Users uploadedBy;

    private java.sql.Timestamp uploadedAt;
}

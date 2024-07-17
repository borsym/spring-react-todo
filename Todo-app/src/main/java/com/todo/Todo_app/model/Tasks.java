package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private Users assignedTo;

    @ManyToOne
    @JoinColumn(name = "priority")
    private Priorities priority;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "status")
    private Status status;

    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
}

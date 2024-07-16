package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;

}
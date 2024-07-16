package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String status;
}

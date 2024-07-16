package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Priorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "priority_name")
    private String priorityName;

}

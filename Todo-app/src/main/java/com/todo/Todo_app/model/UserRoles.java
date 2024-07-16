package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}

package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TeamMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Teams team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}

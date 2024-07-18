package com.todo.Todo_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Teams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;
// TODO check if its req
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
//    private Set<TeamMembers> members = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
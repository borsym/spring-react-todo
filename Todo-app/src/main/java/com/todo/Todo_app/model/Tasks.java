package com.todo.Todo_app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    private String title;
    private String description;
    @ManyToOne
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

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comments> comments = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

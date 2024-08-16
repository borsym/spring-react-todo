package com.todo.todo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "task")
public class TasksEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private UsersEntity assignedTo;
    @ManyToOne
    @JoinColumn(name = "priority")
    private PrioritiesEntity priority;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private UsersEntity createdBy;
    @ManyToOne
    @JoinColumn(name = "status")
    private StatusEntity status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CommentsEntity> comments = new ArrayList<>();

}

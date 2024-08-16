package com.todo.todo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_roles")
public class UserRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RolesEntity role;
}

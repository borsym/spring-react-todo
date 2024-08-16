package com.example.teams.model;

import com.example.teams.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teams")
public class TeamsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "created_by", nullable = false)
    private UUID createdById;
//
//    @ManyToOne
//    @JoinColumn(name = "created_by")
//    private UsersEntity createdBy;
    @Transient
    private UserDTO createdBy;

}
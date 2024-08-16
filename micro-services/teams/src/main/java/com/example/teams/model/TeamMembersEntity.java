package com.example.teams.model;

import com.example.teams.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team_members")
public class TeamMembersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamsEntity team;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Transient
    private UserDTO user;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UsersEntity user;
}

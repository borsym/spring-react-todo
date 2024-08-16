package com.example.teams.repository;


import com.example.teams.model.TeamMembersEntity;
import com.example.teams.model.TeamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMembersEntity, UUID> {
    boolean existsByTeamAndUserId(TeamsEntity team, UUID userId);
    Optional<TeamMembersEntity> findByTeamAndUserId(TeamsEntity team, UUID userId);
}

package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.TeamMembersEntity;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMembersEntity, UUID> {
    boolean existsByTeamAndUser(TeamsEntity team, UsersEntity user);
    Optional<TeamMembersEntity> findByTeamAndUser(TeamsEntity team, UsersEntity user);
}

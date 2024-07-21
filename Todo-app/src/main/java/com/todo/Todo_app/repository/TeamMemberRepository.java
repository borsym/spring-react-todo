package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMembers, UUID> {
    boolean existsByTeamAndUser(Teams team, Users user);
    Optional<TeamMembers> findByTeamAndUser(Teams team, Users user);
}

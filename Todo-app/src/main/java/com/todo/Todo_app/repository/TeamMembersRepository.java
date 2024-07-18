package com.todo.Todo_app.repository;

import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMembersRepository extends JpaRepository<TeamMembers, Long> {
    boolean existsByTeamAndUser(Teams team, Users user);
    TeamMembers findByTeamAndUser(Teams team, Users user);
}

package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {
    List<Teams> getAllTeams();
    Teams createTeam(TeamDTO teamDTO, Users user);
    Optional<Teams> getTeamById(UUID id);
    Teams updateTeam(UUID id, TeamDTO teamDTO);
    void deleteTeam(UUID id);
}

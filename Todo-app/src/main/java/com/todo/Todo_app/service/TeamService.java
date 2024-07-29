package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {
    List<TeamsEntity> getAllTeams();
    TeamsEntity createTeam(TeamDTO teamDTO, UsersEntity user);
    Optional<TeamsEntity> getTeamById(UUID id);
    TeamsEntity updateTeam(UUID id, TeamDTO teamDTO);
    void deleteTeam(UUID id);
}

package com.example.teams.service;


import com.example.teams.dto.TeamDTO;
import com.example.teams.model.TeamsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamService {
    List<TeamsEntity> getAllTeams();
    TeamsEntity createTeam(TeamDTO teamDTO, UUID userId);
    Optional<TeamsEntity> getTeamById(UUID id);
    TeamsEntity updateTeam(UUID id, TeamDTO teamDTO);
    void deleteTeam(UUID id);
}

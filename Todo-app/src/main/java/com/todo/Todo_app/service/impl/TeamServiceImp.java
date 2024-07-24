package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.service.TeamService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
@Transactional
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;

    public TeamServiceImp(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Teams> getAllTeams() {
        return teamRepository.findAll();
    }
    @Override
    public Optional<Teams> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }
    @Override
    public Teams createTeam(TeamDTO teamDTO, Users user) {
        Teams team = Teams.builder().createdBy(user).name(teamDTO.getName()).createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
        return teamRepository.save(team);
    }
    @Override
    public Teams updateTeam(UUID id, TeamDTO teamDTO) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        if (teamDTO.getName() != null) {
            team.setName(teamDTO.getName());
        }
        return teamRepository.save(team);
    }
    @Override
    public void deleteTeam(UUID id) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        teamRepository.delete(team);
    }
}

package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.api.exception.EntityNotFoundException;
import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.service.TeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<TeamsEntity> getAllTeams() {
        return teamRepository.findAll();
    }
    @Override
    public Optional<TeamsEntity> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }
    @Override
    public TeamsEntity createTeam(TeamDTO teamDTO, UsersEntity user) {
        TeamsEntity team = TeamsEntity.builder()
                .createdBy(user)
                .name(teamDTO.getName())
                .build();
        return teamRepository.save(team);
    }
    @Override
    public TeamsEntity updateTeam(UUID id, TeamDTO teamDTO) {
        TeamsEntity team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team"));
        if (teamDTO.getName() != null) {
            team.setName(teamDTO.getName());
        }
        return teamRepository.save(team);
    }
    @Override
    public void deleteTeam(UUID id) {
        TeamsEntity team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team"));
        teamRepository.delete(team);
    }
}

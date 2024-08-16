package com.example.teams.service.imp;

import com.example.teams.dto.TeamDTO;
import com.example.teams.dto.UserDTO;
import com.example.teams.exception.EntityNotFoundException;
import com.example.teams.model.TeamsEntity;
import com.example.teams.repository.TeamRepository;
import com.example.teams.service.TeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final RestTemplate restTemplate;


    @Override
    public List<TeamsEntity> getAllTeams() {
        return teamRepository.findAll();
    }
    @Override
    public Optional<TeamsEntity> getTeamById(UUID id) {
        return teamRepository.findById(id);
    }
    @Override
    public TeamsEntity createTeam(TeamDTO teamDTO, UUID userId) {
        UserDTO user = restTemplate.getForObject("http://todo/api/users/{id}", UserDTO.class, userId);
        if (user == null) {
            throw new EntityNotFoundException("User");
        }
        TeamsEntity team = TeamsEntity.builder()
                .createdById(userId)
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

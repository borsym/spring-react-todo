package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public List<Teams> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Teams> getTeamById(Long id) {
        return teamRepository.findById(id);
    }
    public Teams createTeam(TeamDTO teamDTO, Users user) {
        Teams team = new Teams();
        team.setCreatedBy(user);
        team.setName(teamDTO.getName());

        var now = LocalDateTime.now();
        team.setCreatedAt(now);
        team.setUpdatedAt(now);
        return teamRepository.save(team);
    }

    public Teams updateTeam(Long id, TeamDTO teamDTO) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        if(teamDTO.getName() != null) {
            team.setName(teamDTO.getName());
        }
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        teamRepository.delete(team);
    }
}

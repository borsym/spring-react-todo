package com.example.teams.api.controller.team;

import com.example.teams.dto.TeamDTO;
import com.example.teams.model.TeamsEntity;
import com.example.teams.service.imp.TeamServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImp teamService;

    @GetMapping
    public List<TeamsEntity> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TeamsEntity> createTeam(@Valid @RequestBody TeamDTO teamDTO, @PathVariable UUID userId) {
            TeamsEntity newTeam = teamService.createTeam(teamDTO, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTeam);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamsEntity> updateTeam(@PathVariable UUID id, @RequestBody TeamDTO teamDTO) {
            TeamsEntity updateTeam = teamService.updateTeam(id, teamDTO);
            return ResponseEntity.ok(updateTeam);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID id) {
            teamService.deleteTeam(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }

}

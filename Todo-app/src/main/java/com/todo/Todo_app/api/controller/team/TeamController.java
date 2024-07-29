package com.todo.Todo_app.api.controller.team;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.TeamServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImp teamService;

    @GetMapping
    public List<TeamsEntity> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public ResponseEntity<TeamsEntity> createTeam(@Valid @RequestBody TeamDTO teamDTO, @AuthenticationPrincipal UsersEntity user) {
            TeamsEntity newTeam = teamService.createTeam(teamDTO, user);
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

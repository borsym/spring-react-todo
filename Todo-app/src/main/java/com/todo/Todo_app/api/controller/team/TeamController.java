package com.todo.Todo_app.api.controller.team;

import com.todo.Todo_app.dto.TeamDTO;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.service.impl.TeamServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamServiceImp teamService;

    public TeamController(TeamServiceImp teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamsEntity> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamDTO teamDTO, @AuthenticationPrincipal UsersEntity user) {
        try {
            TeamsEntity newTeam = teamService.createTeam(teamDTO, user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newTeam);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the team");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable UUID id, @RequestBody TeamDTO teamDTO) {
        try {
            TeamsEntity updateTeam = teamService.updateTeam(id, teamDTO);
            return ResponseEntity.ok(updateTeam);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the team");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable UUID id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

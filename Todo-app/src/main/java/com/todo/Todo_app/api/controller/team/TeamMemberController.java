package com.todo.Todo_app.api.controller.team;

import com.todo.Todo_app.model.TeamMembersEntity;
import com.todo.Todo_app.service.impl.TeamMemberServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams/members")
@RequiredArgsConstructor
public class TeamMemberController {
    private final TeamMemberServiceImp teamMembersService;

    // TODO the response from this is not nice
    @GetMapping
    public List<TeamMembersEntity> getAllTeamMembers() {
        return teamMembersService.getAllTeamMembers();
    }

    @PostMapping("/{teamId}/{userId}")
    public ResponseEntity<TeamMembersEntity> addMemberToTeam(@PathVariable(value = "teamId") UUID teamId, @PathVariable(value = "userId") UUID userId) {
        TeamMembersEntity teamMember = teamMembersService.addMemberToTeam(teamId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMember);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable(value = "teamId") UUID teamId, @PathVariable(value = "userId") UUID userId) {
        teamMembersService.deleteTeamMember(teamId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content

    }

}

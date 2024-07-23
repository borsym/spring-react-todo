package com.todo.Todo_app.api.controller.team;

import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.service.impl.TeamMemberServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teams/members")
public class TeamMemberController {
    private final TeamMemberServiceImp teamMembersService;

    public TeamMemberController(TeamMemberServiceImp teamMembersService) {
        this.teamMembersService = teamMembersService;
    }

    // TODO the response from this is not nice
    @GetMapping
    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersService.getAllTeamMembers();
    }

    @PostMapping("/{teamId}/{userId}")
    public ResponseEntity<?> addMemberToTeam(@PathVariable(value = "teamId") UUID teamId, @PathVariable(value = "userId") UUID userId) {
        try {
            TeamMembers teamMember = teamMembersService.addMemberToTeam(teamId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamMember);
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamMember(@PathVariable(value = "teamId") UUID teamId, @PathVariable(value = "userId") UUID userId) {
        try {
            teamMembersService.deleteTeamMember(teamId, userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

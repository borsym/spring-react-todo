package com.todo.Todo_app.api.controller.teammember;

import com.todo.Todo_app.dto.TeamMemberDTO;
import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.service.TeamMembersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams/members")
public class TeamMemberController {
    private final TeamMembersService teamMembersService;

    public TeamMemberController(TeamMembersService teamMembersService) {
        this.teamMembersService = teamMembersService;
    }

    // TODO the response from this is not nice
    @GetMapping
    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersService.getAllTeamMembers();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addMemberToTeam(@PathVariable Long id, @RequestBody TeamMemberDTO teamMemberDTO) {
        TeamMembers teamMember = teamMembersService.addMemberToTeam(id, teamMemberDTO);
        return ResponseEntity.ok(teamMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamMember(@PathVariable Long id, @RequestBody TeamMemberDTO teamMemberDTO) {
        try {
            teamMembersService.deleteTeamMember(id, teamMemberDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}

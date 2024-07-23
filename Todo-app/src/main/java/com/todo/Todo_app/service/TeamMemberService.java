package com.todo.Todo_app.service;

import com.todo.Todo_app.model.TeamMembers;

import java.util.List;
import java.util.UUID;

public interface TeamMemberService {
    List<TeamMembers> getAllTeamMembers();
    TeamMembers addMemberToTeam(UUID teamId, UUID userId);
    void deleteTeamMember(UUID teamId, UUID userId);
}

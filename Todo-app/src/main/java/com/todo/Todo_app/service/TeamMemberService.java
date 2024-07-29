package com.todo.Todo_app.service;

import com.todo.Todo_app.model.TeamMembersEntity;

import java.util.List;
import java.util.UUID;

public interface TeamMemberService {
    List<TeamMembersEntity> getAllTeamMembers();
    TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId);
    void deleteTeamMember(UUID teamId, UUID userId);
}

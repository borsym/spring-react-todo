package com.todo.Todo_app.service;

import com.todo.Todo_app.exception.UserAlreadyMemberOfTeamException;
import com.todo.Todo_app.model.TeamMembersEntity;

import java.util.List;
import java.util.UUID;

public interface TeamMemberService {
    List<TeamMembersEntity> getAllTeamMembers();
    TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId) throws UserAlreadyMemberOfTeamException;
    void deleteTeamMember(UUID teamId, UUID userId);
}

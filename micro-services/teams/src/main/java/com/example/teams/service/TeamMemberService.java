package com.example.teams.service;

import com.example.teams.model.TeamMembersEntity;

import java.util.List;
import java.util.UUID;

public interface TeamMemberService {
    List<TeamMembersEntity> getAllTeamMembers();
    TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId);
    void deleteTeamMember(UUID teamId, UUID userId);
}

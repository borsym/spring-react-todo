package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.exception.UserAlreadyMemberOfTeamException;
import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.TeamMemberRepository;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.service.TeamMemberService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class TeamMemberServiceImp implements TeamMemberService {
    private final TeamMemberRepository teamMembersRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    public TeamMemberServiceImp(TeamMemberRepository teamMembersRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.teamMembersRepository = teamMembersRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Override
    @Transactional
    public TeamMembers addMemberToTeam(UUID teamId, UUID userId) throws UserAlreadyMemberOfTeamException {
        Teams team = findOrThrow(teamRepository, teamId, "Teams");
        Users user = findOrThrow(userRepository, userId, "Users");

        if (teamMembersRepository.existsByTeamAndUser(team, user)) {
            throw new UserAlreadyMemberOfTeamException();
        }

        TeamMembers teamMember = TeamMembers.builder().team(team).user(user).build();

        return teamMembersRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(UUID teamId, UUID userId) {
        Teams team = findOrThrow(teamRepository, teamId, "Teams");
        Users user = findOrThrow(userRepository, userId, "Users");
        TeamMembers teamMember = teamMembersRepository.findByTeamAndUser(team, user).orElseThrow(() -> new RuntimeException("Team member not found"));
        teamMembersRepository.delete(teamMember);
    }

}

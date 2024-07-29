package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.exception.UserAlreadyMemberOfTeamException;
import com.todo.Todo_app.model.TeamMembersEntity;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
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
@Transactional
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
    public List<TeamMembersEntity> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Override
    public TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId) throws UserAlreadyMemberOfTeamException {
        TeamsEntity team = findOrThrow(teamRepository, teamId, "Teams");
        UsersEntity user = findOrThrow(userRepository, userId, "Users");

        if (teamMembersRepository.existsByTeamAndUser(team, user)) {
            throw new UserAlreadyMemberOfTeamException();
        }

        TeamMembersEntity teamMember = TeamMembersEntity.builder().team(team).user(user).build();

        return teamMembersRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(UUID teamId, UUID userId) {
        TeamsEntity team = findOrThrow(teamRepository, teamId, "Teams");
        UsersEntity user = findOrThrow(userRepository, userId, "Users");
        TeamMembersEntity teamMember = teamMembersRepository.findByTeamAndUser(team, user).orElseThrow(() -> new RuntimeException("Team member not found"));
        teamMembersRepository.delete(teamMember);
    }

}

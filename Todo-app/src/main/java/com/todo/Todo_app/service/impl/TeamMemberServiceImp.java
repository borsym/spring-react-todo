package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.api.exception.EntityAlreadyExistsException;
import com.todo.Todo_app.api.exception.EntityNotFoundException;
import com.todo.Todo_app.model.TeamMembersEntity;
import com.todo.Todo_app.model.TeamsEntity;
import com.todo.Todo_app.model.UsersEntity;
import com.todo.Todo_app.repository.TeamMemberRepository;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.repository.UserRepository;
import com.todo.Todo_app.service.TeamMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class TeamMemberServiceImp implements TeamMemberService {
    private final TeamMemberRepository teamMembersRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    @Override
    public List<TeamMembersEntity> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Override
    public TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId)  {
        TeamsEntity team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team"));
        UsersEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));

        if (teamMembersRepository.existsByTeamAndUser(team, user)) {
            throw new EntityAlreadyExistsException("Team and user");
        }

        TeamMembersEntity teamMember = TeamMembersEntity.builder().team(team).user(user).build();

        return teamMembersRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(UUID teamId, UUID userId) {
        TeamsEntity team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team"));
        UsersEntity user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User"));
        TeamMembersEntity teamMember = teamMembersRepository.findByTeamAndUser(team, user).orElseThrow(() -> new EntityNotFoundException("Team member"));
        teamMembersRepository.delete(teamMember);
    }

}

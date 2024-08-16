package com.example.teams.service.imp;

import com.example.teams.exception.EntityAlreadyExistsException;
import com.example.teams.exception.EntityNotFoundException;
import com.example.teams.model.TeamMembersEntity;
import com.example.teams.model.TeamsEntity;
import com.example.teams.repository.TeamMemberRepository;
import com.example.teams.repository.TeamRepository;
import com.example.teams.service.TeamMemberService;
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


    @Override
    public List<TeamMembersEntity> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Override
    public TeamMembersEntity addMemberToTeam(UUID teamId, UUID userId)  {
        TeamsEntity team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team"));

        if (teamMembersRepository.existsByTeamAndUserId(team, userId)) {
            throw new EntityAlreadyExistsException("Team and user");
        }

        TeamMembersEntity teamMember = TeamMembersEntity.builder().team(team).userId(userId).build();

        return teamMembersRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(UUID teamId, UUID userId) {
        TeamsEntity team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Team"));
        TeamMembersEntity teamMember = teamMembersRepository.findByTeamAndUserId(team, userId).orElseThrow(() -> new EntityNotFoundException("Team member"));
        teamMembersRepository.delete(teamMember);
    }

}

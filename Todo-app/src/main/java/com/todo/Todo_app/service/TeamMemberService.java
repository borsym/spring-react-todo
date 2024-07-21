package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TeamMemberDTO;
import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.TeamMemberRepository;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class TeamMemberService {
    private final TeamMemberRepository teamMembersRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    public TeamMemberService(TeamMemberRepository teamMembersRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.teamMembersRepository = teamMembersRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Transactional
    public TeamMembers addMemberToTeam(Long teamId, Long userId) {
        Teams team = findOrThrow(teamRepository, teamId, "Teams");
        Users user = findOrThrow(userRepository, userId, "Users");

        if (teamMembersRepository.existsByTeamAndUser(team, user)) {
            throw new RuntimeException("User is already a member of the team");
        }

        TeamMembers teamMember = new TeamMembers();
        teamMember.setTeam(team);
        teamMember.setUser(user);

        return teamMembersRepository.save(teamMember);
    }

    public void deleteTeamMember(Long teamId, Long userId) {
        Teams team = findOrThrow(teamRepository, teamId, "Teams");
        Users user = findOrThrow(userRepository, userId, "Users");
        TeamMembers teamMember = teamMembersRepository.findByTeamAndUser(team, user)
                .orElseThrow(() -> new RuntimeException("Team member not found"));
        System.out.println(teamMember);
        teamMembersRepository.delete(teamMember);
    }

}

package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TeamMemberDTO;
import com.todo.Todo_app.model.TeamMembers;
import com.todo.Todo_app.model.Teams;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.repository.TeamMembersRepository;
import com.todo.Todo_app.repository.TeamRepository;
import com.todo.Todo_app.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class TeamMembersService {
    private final TeamMembersRepository teamMembersRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    public TeamMembersService(TeamMembersRepository teamMembersRepository, TeamRepository teamRepository, UserRepository userRepository) {
        this.teamMembersRepository = teamMembersRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public List<TeamMembers> getAllTeamMembers() {
        return teamMembersRepository.findAll();
    }

    @Transactional
    public TeamMembers addMemberToTeam(Long id, TeamMemberDTO teamMemberDTO) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        Users user = findOrThrow(userRepository, teamMemberDTO.getIdUser(), "Users");

        if (teamMembersRepository.existsByTeamAndUser(team, user)) {
            throw new RuntimeException("User is already a member of the team");
        }

        TeamMembers teamMember = new TeamMembers();
        teamMember.setTeam(team);
        teamMember.setUser(user);

        return teamMembersRepository.save(teamMember);
    }

    public void deleteTeamMember(Long id, TeamMemberDTO teamMemberDTO) {
        Teams team = findOrThrow(teamRepository, id, "Teams");
        Users user = findOrThrow(userRepository, teamMemberDTO.getIdUser(), "Users");

        TeamMembers teamMember = teamMembersRepository.findByTeamAndUser(team, user);
        System.out.println(teamMember);
        teamMembersRepository.delete(teamMember);
    }

}

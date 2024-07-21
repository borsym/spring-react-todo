package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.model.Priorities;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.repository.PriorityRepository;
import com.todo.Todo_app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Priorities createPriority(PriorityDTO priorityDTO) {
        var priorityName = priorityDTO.getPriorityName();
        if (priorityRepository.findByPriorityName(priorityName).isPresent()) {
            throw new RuntimeException("Priority already exists");
        }
        Priorities priorities = new Priorities();
        priorities.setPriorityName(priorityName);
        return priorityRepository.save(priorities);
    }

    public List<Priorities> getAllPriority() {
        return priorityRepository.findAll();
    }

    public void deletePriority(UUID id) {
        Priorities priorities = priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role doesn't exists"));
        priorityRepository.delete(priorities);
    }
}

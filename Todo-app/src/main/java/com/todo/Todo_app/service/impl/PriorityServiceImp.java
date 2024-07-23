package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.model.Priorities;
import com.todo.Todo_app.repository.PriorityRepository;
import com.todo.Todo_app.service.PriorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriorityServiceImp implements PriorityService {
    private final PriorityRepository priorityRepository;

    public PriorityServiceImp(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }
    @Override
    public Priorities createPriority(PriorityDTO priorityDTO) {
        var priorityName = priorityDTO.getPriorityName();
        if (priorityRepository.findByPriorityName(priorityName).isPresent()) {
            throw new RuntimeException("Priority already exists");
        }
        Priorities priorities = Priorities.builder().priorityName(priorityName).build();
        return priorityRepository.save(priorities);
    }
    @Override
    public List<Priorities> getAllPriority() {
        return priorityRepository.findAll();
    }
    @Override
    public void deletePriority(UUID id) {
        Priorities priorities = priorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role doesn't exists"));
        priorityRepository.delete(priorities);
    }
}

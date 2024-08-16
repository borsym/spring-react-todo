package com.todo.todo.service;

import com.todo.todo.dto.PriorityDTO;
import com.todo.todo.model.PrioritiesEntity;

import java.util.List;
import java.util.UUID;

public interface PriorityService {
    PrioritiesEntity createPriority(PriorityDTO priorityDTO);
    List<PrioritiesEntity> getAllPriority();
    void deletePriority(UUID id);
}

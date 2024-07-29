package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.model.PrioritiesEntity;

import java.util.List;
import java.util.UUID;

public interface PriorityService {
    PrioritiesEntity createPriority(PriorityDTO priorityDTO);
    List<PrioritiesEntity> getAllPriority();
    void deletePriority(UUID id);
}

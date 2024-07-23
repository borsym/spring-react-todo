package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.PriorityDTO;
import com.todo.Todo_app.model.Priorities;

import java.util.List;
import java.util.UUID;

public interface PriorityService {
    Priorities createPriority(PriorityDTO priorityDTO);
    List<Priorities> getAllPriority();
    void deletePriority(UUID id);
}

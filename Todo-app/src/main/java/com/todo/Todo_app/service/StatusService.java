package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.model.StatusEntity;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    StatusEntity createStatus(StatusDTO statusDTO);
    List<StatusEntity> getAllStatus();
    void deleteStatus(UUID id);
}

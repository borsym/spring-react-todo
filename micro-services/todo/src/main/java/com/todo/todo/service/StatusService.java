package com.todo.todo.service;

import com.todo.todo.dto.StatusDTO;
import com.todo.todo.model.StatusEntity;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    StatusEntity createStatus(StatusDTO statusDTO);
    List<StatusEntity> getAllStatus();
    void deleteStatus(UUID id);
}

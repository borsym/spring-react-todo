package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.exception.StatusNotFoundException;
import com.todo.Todo_app.model.Status;

import java.util.List;
import java.util.UUID;

public interface StatusService {
    Status createStatus(StatusDTO statusDTO);
    List<Status> getAllStatus();
    void deleteStatus(UUID id) throws StatusNotFoundException;
}

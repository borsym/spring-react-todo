package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.model.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Tasks> getAllTasks();
    Optional<Tasks> getTaskById(UUID id);
    Tasks createTask(TaskDTO taskDTO, Users user);
    Tasks updateTask(UUID id, TaskDTO taskDTO);
    void deleteTask(UUID id);
}

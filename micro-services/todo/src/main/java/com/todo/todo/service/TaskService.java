package com.todo.todo.service;

import com.todo.todo.dto.TaskDTO;
import com.todo.todo.model.TasksEntity;
import com.todo.todo.model.UsersEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<TasksEntity> getAllTasks();
    Optional<TasksEntity> getTaskById(UUID id);
    TasksEntity createTask(TaskDTO taskDTO, UsersEntity user);
    TasksEntity updateTask(UUID id, TaskDTO taskDTO);
    void deleteTask(UUID id);
}

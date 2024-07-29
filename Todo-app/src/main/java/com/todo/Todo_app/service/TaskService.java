package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.TasksEntity;
import com.todo.Todo_app.model.UsersEntity;

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

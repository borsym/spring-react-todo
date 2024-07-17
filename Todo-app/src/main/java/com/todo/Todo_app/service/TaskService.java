package com.todo.Todo_app.service;

import com.todo.Todo_app.model.Tasks;
import com.todo.Todo_app.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }
    public Optional<Tasks> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}

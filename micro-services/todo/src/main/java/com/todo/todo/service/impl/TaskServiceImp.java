package com.todo.todo.service.impl;

import com.todo.todo.api.exception.EntityNotFoundException;
import com.todo.todo.dto.TaskDTO;
import com.todo.todo.model.TasksEntity;
import com.todo.todo.model.UsersEntity;
import com.todo.todo.repository.*;
import com.todo.todo.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<TasksEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<TasksEntity> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public TasksEntity createTask(TaskDTO taskDTO, UsersEntity user) {
        TasksEntity.TasksEntityBuilder taskBuilder = TasksEntity.builder().title(taskDTO.getTitle()).description(taskDTO.getDescription()).project(projectRepository.findById(taskDTO.getProjectId()).orElseThrow(() -> new EntityNotFoundException("Project"))).createdBy(user);

        Optional.ofNullable(taskDTO.getPriority()).map(priorityId -> priorityRepository.findById(priorityId).orElseThrow(() -> new EntityNotFoundException("Priorities"))).ifPresent(taskBuilder::priority);

        Optional.ofNullable(taskDTO.getAssignedTo()).map(assignedToId -> userRepository.findById(assignedToId).orElseThrow(() -> new EntityNotFoundException("User"))).ifPresent(taskBuilder::assignedTo);

        Optional.ofNullable(taskDTO.getStatus()).map(statusId -> statusRepository.findById(statusId).orElseThrow(() -> new EntityNotFoundException("Status"))).ifPresent(taskBuilder::status);

        return taskRepository.save(taskBuilder.build());
    }

    @Override
    public TasksEntity updateTask(UUID id, TaskDTO taskDTO) {
        TasksEntity existingTask = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tasks"));

        TasksEntity.TasksEntityBuilder taskBuilder = existingTask.toBuilder();

        Optional.ofNullable(taskDTO.getTitle()).ifPresent(taskBuilder::title);
        Optional.ofNullable(taskDTO.getDescription()).ifPresent(taskBuilder::description);

        Optional.ofNullable(taskDTO.getProjectId()).map(projectId -> projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Projects"))).ifPresent(taskBuilder::project);

        Optional.ofNullable(taskDTO.getPriority()).map(priorityId -> priorityRepository.findById(priorityId).orElseThrow(() -> new EntityNotFoundException("Priorities"))).ifPresent(taskBuilder::priority);

        Optional.ofNullable(taskDTO.getAssignedTo()).map(assignedToId -> userRepository.findById(assignedToId).orElseThrow(() -> new EntityNotFoundException("Users"))).ifPresent(taskBuilder::assignedTo);

        Optional.ofNullable(taskDTO.getStatus()).map(statusId -> statusRepository.findById(statusId).orElseThrow(() -> new EntityNotFoundException("Status"))).ifPresent(taskBuilder::status);

        return taskRepository.save(taskBuilder.build());
    }

    @Override
    public void deleteTask(UUID id) {
        TasksEntity task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tasks"));
        taskRepository.delete(task);
    }


}

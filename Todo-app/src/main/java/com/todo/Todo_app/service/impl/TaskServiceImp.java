package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.*;
import com.todo.Todo_app.repository.*;
import com.todo.Todo_app.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

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
        TasksEntity.TasksEntityBuilder taskBuilder = TasksEntity.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .project(findOrThrow(projectRepository, taskDTO.getProjectId(), "Projects"))
                .createdBy(user);

        Optional.ofNullable(taskDTO.getPriority()).map(priorityId -> findOrThrow(priorityRepository, priorityId, "Priorities")).ifPresent(taskBuilder::priority);

        Optional.ofNullable(taskDTO.getAssignedTo()).map(assignedToId -> findOrThrow(userRepository, assignedToId, "Users")).ifPresent(taskBuilder::assignedTo);

        Optional.ofNullable(taskDTO.getStatus()).map(statusId -> findOrThrow(statusRepository, statusId, "Status")).ifPresent(taskBuilder::status);

        return taskRepository.save(taskBuilder.build());
    }
    @Override
    public TasksEntity updateTask(UUID id, TaskDTO taskDTO) {
        TasksEntity existingTask = findOrThrow(taskRepository, id, "Tasks");

        TasksEntity.TasksEntityBuilder taskBuilder = existingTask.toBuilder();

        Optional.ofNullable(taskDTO.getTitle()).ifPresent(taskBuilder::title);
        Optional.ofNullable(taskDTO.getDescription()).ifPresent(taskBuilder::description);

        Optional.ofNullable(taskDTO.getProjectId())
                .map(projectId -> findOrThrow(projectRepository, projectId, "Projects"))
                .ifPresent(taskBuilder::project);

        Optional.ofNullable(taskDTO.getPriority())
                .map(priorityId -> findOrThrow(priorityRepository, priorityId, "Priorities"))
                .ifPresent(taskBuilder::priority);

        Optional.ofNullable(taskDTO.getAssignedTo())
                .map(assignedToId -> findOrThrow(userRepository, assignedToId, "Users"))
                .ifPresent(taskBuilder::assignedTo);

        Optional.ofNullable(taskDTO.getStatus())
                .map(statusId -> findOrThrow(statusRepository, statusId, "Status"))
                .ifPresent(taskBuilder::status);

        return taskRepository.save(taskBuilder.build());
    }
    @Override
    public void deleteTask(UUID id) {
        TasksEntity task = findOrThrow(taskRepository, id, "Tasks");
        taskRepository.delete(task);
    }


}

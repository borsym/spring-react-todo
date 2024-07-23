package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.*;
import com.todo.Todo_app.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.todo.Todo_app.utils.Utils.findOrThrow;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final StatusRepository statusRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, PriorityRepository priorityRepository, StatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.priorityRepository = priorityRepository;
        this.statusRepository = statusRepository;
    }


    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Tasks> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    public Tasks createTask(TaskDTO taskDTO, Users user) {
        Tasks.TasksBuilder taskBuilder = Tasks.builder().title(taskDTO.getTitle()).description(taskDTO.getDescription()).project(findOrThrow(projectRepository, taskDTO.getProjectId(), "Projects")).createdBy(user).updatedAt(LocalDateTime.now()).createdAt(LocalDateTime.now());

        Optional.ofNullable(taskDTO.getPriority()).map(priorityId -> findOrThrow(priorityRepository, priorityId, "Priorities")).ifPresent(taskBuilder::priority);

        Optional.ofNullable(taskDTO.getAssignedTo()).map(assignedToId -> findOrThrow(userRepository, assignedToId, "Users")).ifPresent(taskBuilder::assignedTo);

        Optional.ofNullable(taskDTO.getStatus()).map(statusId -> findOrThrow(statusRepository, statusId, "Status")).ifPresent(taskBuilder::status);

        return taskRepository.save(taskBuilder.build());
    }

    public Tasks updateTask(UUID id, TaskDTO taskDTO) {
        Tasks existingTask = findOrThrow(taskRepository, id, "Tasks");

        Tasks.TasksBuilder taskBuilder = existingTask.toBuilder();

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

        taskBuilder.updatedAt(LocalDateTime.now());

        return taskRepository.save(taskBuilder.build());
    }

    public void deleteTask(UUID id) {
        Tasks task = findOrThrow(taskRepository, id, "Tasks");
        taskRepository.delete(task);
    }


}

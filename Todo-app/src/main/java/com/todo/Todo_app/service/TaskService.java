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

    // TODO use builder
    public Tasks createTask(TaskDTO taskDTO, Users user) {
        Tasks task = new Tasks();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        Projects project = findOrThrow(projectRepository, taskDTO.getProjectId(), "Projects");
        task.setProject(project);

        task.setCreatedBy(user);

        var now = LocalDateTime.now();
        task.setUpdatedAt(now);
        task.setCreatedAt(now);

        if (taskDTO.getPriority() != null) {
            Priorities priorities = findOrThrow(priorityRepository, taskDTO.getPriority(), "Priorities");
            task.setPriority(priorities);
        }
        if (taskDTO.getAssignedTo() != null) {
            Users assigned_user = findOrThrow(userRepository, taskDTO.getAssignedTo(), "Users");
            task.setAssignedTo(assigned_user);
        }

        if (taskDTO.getStatus() != null) {
            Status status = findOrThrow(statusRepository, taskDTO.getStatus(), "Status");
            task.setStatus(status);
        }

        return taskRepository.save(task);
    }

    public Tasks updateTask(UUID id, TaskDTO taskDTO) {

        Tasks task = findOrThrow(taskRepository, id, "Tasks");

        if (taskDTO.getTitle() != null) {
            task.setTitle(taskDTO.getTitle());
        }

        if (taskDTO.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getProjectId() != null) {
            Projects project = findOrThrow(projectRepository, taskDTO.getProjectId(), "Projects");
            task.setProject(project);
        }

        if (taskDTO.getPriority() != null) {
            Priorities priority = findOrThrow(priorityRepository, taskDTO.getPriority(), "Priorities");
            task.setPriority(priority);
        }

        if (taskDTO.getAssignedTo() != null) {
            Users assignedUser = findOrThrow(userRepository, taskDTO.getAssignedTo(), "Users");
            task.setAssignedTo(assignedUser);
        }

        if (taskDTO.getStatus() != null) {
            Status status = findOrThrow(statusRepository, taskDTO.getStatus(), "Status");
            task.setStatus(status);
        }

        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        Tasks task = findOrThrow(taskRepository, id, "Tasks");
        taskRepository.delete(task);
    }


}

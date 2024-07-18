package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.TaskDTO;
import com.todo.Todo_app.model.*;
import com.todo.Todo_app.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private PriorityRepository priorityRepository;
    private StatusRepository statusRepository;
    // import other repository?, servies makes no sens.


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
    public Optional<Tasks> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Tasks addTask(TaskDTO taskDTO) {
        Tasks task = new Tasks();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        Projects project = findOrThrow(projectRepository, taskDTO.getProjectId(), "Projects");
        task.setProject(project);

        Users user = findOrThrow(userRepository, taskDTO.getCreatedBy(), "Users");
        task.setCreatedBy(user);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        task.setUpdatedAt(now);
        task.setCreatedAt(now);

        if(taskDTO.getPriority() != null) {
            Priorities priorities = findOrThrow(priorityRepository, taskDTO.getPriority(), "Priorities");
            task.setPriority(priorities);
        }
        if(taskDTO.getAssignedTo() != null) {
            Users assigned_user = findOrThrow(userRepository, taskDTO.getAssignedTo(), "Users");
            task.setAssignedTo(assigned_user);
        }

        if(taskDTO.getStatus() != null) {
            Status status = findOrThrow(statusRepository, taskDTO.getStatus(), "Status");
            task.setStatus(status);
        }

        return taskRepository.save(task);
    }

    public static <T, ID> T findOrThrow(JpaRepository<T, ID> repository, ID id, String entityName) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException(entityName + " with id " + id + " not found"));
    }
}

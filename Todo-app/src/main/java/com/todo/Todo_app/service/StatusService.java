package com.todo.Todo_app.service;

import com.todo.Todo_app.dto.RolesDTO;
import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.model.Roles;
import com.todo.Todo_app.model.Status;
import com.todo.Todo_app.repository.RoleRepository;
import com.todo.Todo_app.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status createStatus(StatusDTO statusDTO) {
        var statusName = statusDTO.getStatusName();
        if (statusRepository.findBystatusName(statusName)) {
            throw new RuntimeException("Role already exists");
        }
        Status status = Status.builder().statusName(statusName).build();
        return statusRepository.save(status);
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public void deleteStatus(UUID id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status doesn't exists"));
        statusRepository.delete(status);
    }
}

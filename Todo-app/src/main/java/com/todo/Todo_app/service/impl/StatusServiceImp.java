package com.todo.Todo_app.service.impl;

import com.todo.Todo_app.dto.StatusDTO;
import com.todo.Todo_app.exception.StatusNotFoundException;
import com.todo.Todo_app.model.StatusEntity;
import com.todo.Todo_app.repository.StatusRepository;
import com.todo.Todo_app.service.StatusService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StatusServiceImp implements StatusService {
    private final StatusRepository statusRepository;

    public StatusServiceImp(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }
    @Override
    public StatusEntity createStatus(StatusDTO statusDTO) {
        var statusName = statusDTO.getStatusName();
        if (statusRepository.findBystatusName(statusName)) {
            throw new RuntimeException("Role already exists");
        }
        StatusEntity status = StatusEntity.builder().statusName(statusName).build();
        return statusRepository.save(status);
    }
    @Override
    public List<StatusEntity> getAllStatus() {
        return statusRepository.findAll();
    }
    @Override
    public void deleteStatus(UUID id) throws StatusNotFoundException {
        StatusEntity status = statusRepository.findById(id)
                .orElseThrow(StatusNotFoundException::new);
        statusRepository.delete(status);
    }
}

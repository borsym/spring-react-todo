package com.todo.todo.service.impl;

import com.todo.todo.api.exception.EntityAlreadyExistsException;
import com.todo.todo.api.exception.EntityNotFoundException;
import com.todo.todo.dto.StatusDTO;
import com.todo.todo.model.StatusEntity;
import com.todo.todo.repository.StatusRepository;
import com.todo.todo.service.StatusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StatusServiceImp implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public StatusEntity createStatus(StatusDTO statusDTO) {
        val statusName = statusDTO.getStatusName();
        if (statusRepository.findBystatusName(statusName)) {
            throw new EntityAlreadyExistsException("Role");
        }
        StatusEntity status = StatusEntity.builder().statusName(statusName).build();
        return statusRepository.save(status);
    }
    @Override
    public List<StatusEntity> getAllStatus() {
        return statusRepository.findAll();
    }
    @Override
    public void deleteStatus(UUID id)  {
        StatusEntity status = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status"));
        statusRepository.delete(status);
    }
}

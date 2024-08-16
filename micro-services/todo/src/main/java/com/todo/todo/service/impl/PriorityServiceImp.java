package com.todo.todo.service.impl;

import com.todo.todo.api.exception.EntityNotFoundException;
import com.todo.todo.dto.PriorityDTO;
import com.todo.todo.model.PrioritiesEntity;
import com.todo.todo.repository.PriorityRepository;
import com.todo.todo.service.PriorityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PriorityServiceImp implements PriorityService {
    private final PriorityRepository priorityRepository;

    @Override
    public PrioritiesEntity createPriority(PriorityDTO priorityDTO) {
        val priorityName = priorityDTO.getPriorityName();
        priorityRepository.findByPriorityName(priorityName).orElseThrow(() -> new EntityNotFoundException("Priorities"));
        PrioritiesEntity priorities = PrioritiesEntity.builder().priorityName(priorityName).build();
        return priorityRepository.save(priorities);
    }

    @Override
    public List<PrioritiesEntity> getAllPriority() {
        return priorityRepository.findAll();
    }

    @Override
    public void deletePriority(UUID id) {
        PrioritiesEntity priorities = priorityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Priorities"));
        priorityRepository.delete(priorities);
    }
}

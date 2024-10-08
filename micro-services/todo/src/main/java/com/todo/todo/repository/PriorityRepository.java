package com.todo.todo.repository;

import com.todo.todo.model.PrioritiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PriorityRepository extends JpaRepository<PrioritiesEntity, UUID> {
    Optional<PrioritiesEntity> findByPriorityName(String priorityName);
}

package com.todo.todo.utils;

import org.springframework.data.jpa.repository.JpaRepository;

public class Utils {
    public static <T, ID> T findOrThrow(JpaRepository<T, ID> repository, ID id, String entityName) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(entityName + " with id " + id + " not found"));
    }
}

package com.example.teams.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String entityName, HttpStatus status) {
        super(entityName + " not found ", status);
    }

    public EntityNotFoundException(String entityName) {
        super(entityName + " not found ", HttpStatus.NOT_FOUND);
    }

}

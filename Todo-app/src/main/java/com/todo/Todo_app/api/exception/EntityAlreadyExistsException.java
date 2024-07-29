package com.todo.Todo_app.api.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends CustomException {
    public EntityAlreadyExistsException(String entityName, HttpStatus status) {
        super(entityName + " already exists ", status);
    }

    public EntityAlreadyExistsException(String entityName) {
        super(entityName + " already exists ", HttpStatus.CONFLICT);
    }

}

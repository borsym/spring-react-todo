package com.example.teams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorObject> handleCustomException(CustomException ex, WebRequest request) {
        ErrorObject errorObject = ErrorObject.builder()
                .statusCode(ex.getStatus().value())
                .message(ex.getMessage())
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(errorObject, ex.getStatus());
    }
//
//    @ExceptionHandler(ProjectNotFoundException.class)
//    public ResponseEntity<ErrorObject> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(TaskNotFoundException.class)
//    public ResponseEntity<ErrorObject> handleTaskNotFoundException(TaskNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(CommentNotFoundException.class)
//    public ResponseEntity<ErrorObject> handleTaskNotFoundException(CommentNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(PriorityNameNotFoundException.class)
//    public ResponseEntity<ErrorObject> handlePriorityNameNotFoundException(PriorityNameNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(PriorityNotFoundException.class)
//    public ResponseEntity<ErrorObject> handlePriorityNotFoundException(PriorityNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(RoleNotFoundException.class)
//    public ResponseEntity<ErrorObject> handlePriorityNotFoundException(RoleNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(StatusNotFoundException.class)
//    public ResponseEntity<ErrorObject> handleStatusNotFoundException(StatusNotFoundException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
//    }
//
//
//    @ExceptionHandler(ProjectAlreadyExistsException.class)
//    public ResponseEntity<ErrorObject> handleProjectAlreadyExistsException(ProjectAlreadyExistsException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.CONFLICT.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(UserAlreadyExistsException.class)
//    public ResponseEntity<ErrorObject> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
//        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.CONFLICT.value()).message(ex.getMessage()).timestamp(new Date()).build();
//
//        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGlobalException(Exception ex, WebRequest request) {
        ErrorObject errorObject = ErrorObject.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.getMessage()).timestamp(new Date()).build();

        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

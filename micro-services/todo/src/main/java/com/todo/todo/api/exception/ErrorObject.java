package com.todo.todo.api.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}

package com.todo.Todo_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private UUID projectId;
    private UUID assignedTo;
    private UUID priority;
    private UUID status;
}

package com.todo.Todo_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Long projectId;
    private Long assignedTo;
    private Long priority;
    private Long status;
}

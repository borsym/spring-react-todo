package com.todo.Todo_app.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 150, message = "Title must be between 1 and 100 characters")
    private String title;
    @Size(max = 4096, message = "Description must be less than 4096 characters")
    private String description;
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    @NotNull(message = "Created By cannot be null")
    private Long createdBy;
    private Long assignedTo;
    private Long priority;
    private Long status;
}

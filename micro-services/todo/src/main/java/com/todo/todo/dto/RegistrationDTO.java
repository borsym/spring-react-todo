package com.todo.todo.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDTO {
    private String username;
    private String email;
    private String password;
}

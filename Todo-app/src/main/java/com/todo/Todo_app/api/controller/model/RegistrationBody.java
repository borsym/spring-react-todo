package com.todo.Todo_app.api.controller.model;


import lombok.Data;

@Data
public class RegistrationBody {
    private String username;
    private String email;
    private String password;
}

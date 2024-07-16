package com.todo.Todo_app.api.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class Test {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }
}

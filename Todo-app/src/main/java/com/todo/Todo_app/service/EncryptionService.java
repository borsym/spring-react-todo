package com.todo.Todo_app.service;

public interface EncryptionService {
    String encryptPassword(String password);
    boolean verifyPassword(String password, String hash);
}

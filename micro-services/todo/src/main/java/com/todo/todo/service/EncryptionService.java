package com.todo.todo.service;

public interface EncryptionService {
    String encryptPassword(String password);
    boolean verifyPassword(String password, String hash);
}

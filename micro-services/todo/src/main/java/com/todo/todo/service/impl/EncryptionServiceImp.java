package com.todo.todo.service.impl;

import com.todo.todo.service.EncryptionService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EncryptionServiceImp implements EncryptionService {
    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    private String salt;

    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }
    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }
    @Override
    public boolean verifyPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}

package com.todo.Todo_app.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.todo.Todo_app.model.Users;
import com.todo.Todo_app.service.JWTService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTServiceImp implements JWTService {
    private static final String USERNAME_KEY = "USERNAME";
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;
    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    @Override
    public String generateJWT(Users user) {
        return JWT.create().withClaim(USERNAME_KEY, user.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + (1000L * expiryInSeconds))).withIssuer(issuer).sign(algorithm);
    }

    @Override
    public String getUsername(String token) {
        DecodedJWT jwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return jwt.getClaim(USERNAME_KEY).asString();
    }
}

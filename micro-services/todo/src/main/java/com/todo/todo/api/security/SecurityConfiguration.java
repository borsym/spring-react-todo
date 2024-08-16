package com.todo.todo.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable);
        // We need to make sure our authentication filter is run before the http request filter is run.
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
//        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/register", "/api/auth/login", "/api/tasks")
//                .permitAll()
//                .anyRequest()
//                .authenticated());
        http.authorizeHttpRequests(a -> a.anyRequest().permitAll());

        return http.build();
    }
}

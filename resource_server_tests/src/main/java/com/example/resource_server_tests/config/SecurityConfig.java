package com.example.resource_server_tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(resServer -> {
            resServer.jwt(jwtConfigurer -> jwtConfigurer.jwkSetUri("http://example.com"));
        });

        http.authorizeHttpRequests(r -> r.anyRequest().authenticated());

        return http.build();
    }
}

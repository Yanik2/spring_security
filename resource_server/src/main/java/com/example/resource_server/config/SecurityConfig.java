package com.example.resource_server.config;

import com.example.resource_server.converter.TokenConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${jwkuri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requestConfig -> requestConfig.anyRequest().authenticated())
                .oauth2ResourceServer(serverConfig -> {
                    serverConfig.jwt(jwtConfig -> jwtConfig.jwkSetUri(jwksUri)
                            .jwtAuthenticationConverter(new TokenConverter()));
                });
        return http.build();
    }
}

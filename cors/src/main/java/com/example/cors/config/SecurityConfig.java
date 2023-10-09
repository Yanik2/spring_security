package com.example.cors.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(r -> r.anyRequest().permitAll());

        http.cors(c -> {
            c.configurationSource(r -> {
                final var cc = new CorsConfiguration();

                cc.addAllowedOrigin("http://localhost:8080");
//                cc.setAllowedOrigins();

                return cc;
            });
        });
        return http.build();
    }
}

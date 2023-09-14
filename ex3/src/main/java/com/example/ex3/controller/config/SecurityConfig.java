package com.example.ex3.controller.config;

import com.example.ex3.controller.config.filter.CustomSecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final CustomSecurityFilter customSecurityFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .addFilterAt(customSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requestConfig ->
                        requestConfig.anyRequest().authenticated()
                )
                .build();
    }
}

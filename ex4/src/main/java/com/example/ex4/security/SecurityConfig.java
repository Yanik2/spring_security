package com.example.ex4.security;

import com.example.ex4.security.filter.ApiKeyFilter;
import com.example.ex4.security.filter.UsernamePasswordFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final ApiKeyFilter apiKeyFilter;
    private final UsernamePasswordFilter usernamePasswordFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final var filterChain = http
                .httpBasic(Customizer.withDefaults())
//                .addFilterAt(apiKeyFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterAfter(usernamePasswordFilter, ApiKeyFilter.class)
                .authorizeHttpRequests(requestConfig -> {
                    requestConfig
//                            .requestMatchers("/demo")
//                            .hasAuthority("read")
                            .anyRequest().authenticated();
                })
                .build();
        return filterChain;
    }
}

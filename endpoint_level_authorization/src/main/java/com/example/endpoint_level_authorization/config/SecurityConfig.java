package com.example.endpoint_level_authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(c -> {
                    c.anyRequest().permitAll();
                })
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService()  {
//        final var ud = new InMemoryUserDetailsManager();
//
//        final var user = User.builder().username("jim").password("1234").authorities("ROLE_ONE").build();
//
//        ud.createUser(user);
//
//        return ud;
//    }
}

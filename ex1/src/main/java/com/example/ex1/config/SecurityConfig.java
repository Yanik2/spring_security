package com.example.ex1.config;

import com.example.ex1.passwordencoder.CustomPasswordEncoder;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        final var userDetails = new InMemoryUserDetailsManager();
        final var user = User.builder()
                .username("yan")
                .password("asdf ")
                .authorities("read", "write")
                .build();

        userDetails.createUser(user);
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
}

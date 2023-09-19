package com.example.method_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity//@PreAuthhorize @PostAuthorize @PreFilter @PostFilter
//securedEnabled = @Secured
//jsr250enabled = @RolesAllowed
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(requestConfig ->
                    requestConfig.anyRequest().authenticated()
                ).build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        final var ud = User.withUsername("jim").password(passwordEncoder.encode("1234")).authorities("read").build();

        final var ud1 = User.withUsername("pam").password(passwordEncoder.encode("1234")).authorities("write").build();

        final var uds = new InMemoryUserDetailsManager();

        uds.createUser(ud);
        uds.createUser(ud1);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

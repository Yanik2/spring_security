package com.example.opaque_resource_server.config;

import com.example.opaque_resource_server.converter.CustomOpaqueTokenAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .oauth2ResourceServer(serverConfig -> {
                    serverConfig.opaqueToken(tokenConfig -> {
                        tokenConfig.introspectionUri("http://localhost:8080/oauth2/introspect").authenticationConverter(new CustomOpaqueTokenAuthConverter())
                                .introspectionClientCredentials("client", "secret");

                    });
                })
                .authorizeHttpRequests(requestConfig -> {
                    requestConfig.anyRequest().authenticated();
                }).build();
    }
}

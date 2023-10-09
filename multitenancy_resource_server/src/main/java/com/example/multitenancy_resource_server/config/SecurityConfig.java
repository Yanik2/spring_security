package com.example.multitenancy_resource_server.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${introspection-uri}")
    private String introspectionUri;
    @Value("${client}")
    private String client;
    @Value("${secret}")
    private String secret;
    @Value("${jwkset-uri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(rs -> {
            rs.authenticationManagerResolver(resolver(jwtDecoder(), opaqueTokenIntrospector()));
        });

        http.authorizeHttpRequests(r -> r.anyRequest().authenticated());


        return http.build();
    }

    @Bean
    public AuthenticationManagerResolver<HttpServletRequest> resolver(
            JwtDecoder jwtDecoder, OpaqueTokenIntrospector opaqueTokenIntrospector
    ) {
        final var jwtAuth = new ProviderManager(
                new JwtAuthenticationProvider(jwtDecoder)
        );
        final var opaqueAuth = new ProviderManager(
                new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector)
        );

        return (request) -> {
            if ("jwt".equals(request.getHeader("type")))
                return jwtAuth;
            else
                return opaqueAuth;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
    }

    @Bean
    public OpaqueTokenIntrospector opaqueTokenIntrospector() {
        return new SpringOpaqueTokenIntrospector(introspectionUri, client, secret);
    }
}

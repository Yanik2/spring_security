package com.example.ex4.security.provider;

import com.example.ex4.security.authentication.ApiKeyAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {
    @Value("${secret.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Api key provider");
        final var apiAuth = (ApiKeyAuthentication) authentication;

        if (key.equals(apiAuth.getKey())) {
            authentication.setAuthenticated(true);
        }

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthentication.class.equals(authentication);
    }
}

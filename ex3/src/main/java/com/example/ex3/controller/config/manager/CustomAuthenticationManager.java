package com.example.ex3.controller.config.manager;

import com.example.ex3.controller.config.authentication.CustomAuthentication;
import com.example.ex3.controller.config.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationManager implements AuthenticationManager {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (customAuthenticationProvider.supports(authentication.getClass()))
            return customAuthenticationProvider.authenticate(authentication);

        throw new BadCredentialsException("provider doesn't supports");
    }
}

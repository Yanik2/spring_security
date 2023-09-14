package com.example.ex4.security.provider;

import com.example.ex4.security.authentication.UsernamePasswordAuthentication;
import com.example.ex4.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UsernamePasswordProvider implements AuthenticationProvider {
    private final UserDetailsService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Username and passwrod provider");
        final var userAuth = (UsernamePasswordAuthentication) authentication;

        final var dbUser = userService.loadUserByUsername(userAuth.getUsername());

        if (userAuth.getPassword().equals(dbUser.getPassword())) {
            final var authenticatedUser = new UsernamePasswordAuthentication(userAuth.getUsername(), null);
            authenticatedUser.setAuthenticated(true);
            return authenticatedUser;
        }

        return userAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}

package com.example.ex4.security.filter;

import com.example.ex4.security.converter.RequestCredentialsConverter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UsernamePasswordFilter extends OncePerRequestFilter {
    private final AuthenticationManager customAuthenticationManager;
    private final AuthenticationConverter requestCredentialsConverter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var requestAuth = requestCredentialsConverter.convert(request);

        if (requestAuth == null) {
            filterChain.doFilter(request, response);
            return;
        }


        final var checkedAuth = customAuthenticationManager.authenticate(requestAuth);

        if (checkedAuth.isAuthenticated()) {
            final var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(checkedAuth);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }
}

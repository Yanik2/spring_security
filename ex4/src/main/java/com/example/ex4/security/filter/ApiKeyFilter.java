package com.example.ex4.security.filter;

import com.example.ex4.security.authentication.ApiKeyAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ApiKeyFilter extends OncePerRequestFilter {
    private final AuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var key = request.getHeader("api-key");

//        if (key == null) {
//            filterChain.doFilter(request, response);
//        }

        final var auth = new ApiKeyAuthentication(key);
        auth.setAuthenticated(false);

        customAuthenticationManager.authenticate(auth);

        if (auth.isAuthenticated()) {
            final var context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(auth);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);
    }
}

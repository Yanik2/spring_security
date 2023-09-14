package com.example.ex4.security.converter;

import com.example.ex4.security.authentication.UsernamePasswordAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class RequestCredentialsConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        final var header = request.getHeader("Authorization");
        if (!StringUtils.hasText(header))
            return null;

        final var encodedCreds = header.substring(6);
        final var decoded = Base64.getDecoder().decode(encodedCreds.getBytes());
        final var decodedCreds = new String(decoded, StandardCharsets.UTF_8);

        final var index = decodedCreds.indexOf(":");

        final var username = decodedCreds.substring(0, index);
        final var password = decodedCreds.substring(index + 1);

        final var auth = new UsernamePasswordAuthentication(username, password);
        auth.setAuthenticated(false);

        return auth;
    }
}

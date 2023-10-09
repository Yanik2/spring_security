package com.example.csrf.repository;

import com.example.csrf.token.CustomCsrfToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;


public class CustomCsrfRepository implements CsrfTokenRepository {
    private CsrfToken token;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new CustomCsrfToken("token_value", "CSRF_HEADER_NAME", "_csrf");
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        this.token = token;
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        return this.token;
    }
}

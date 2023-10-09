package com.example.csrf.token;

import org.springframework.security.web.csrf.CsrfToken;

public class CustomCsrfToken implements CsrfToken {
    private final String token;
    private final String header;
    private final String parameter;

    public CustomCsrfToken(String token, String header, String parameter) {
        this.token = token;
        this.header = header;
        this.parameter = parameter;
    }

    @Override
    public String getHeaderName() {
        return header;
    }

    @Override
    public String getParameterName() {
        return parameter;
    }

    @Override
    public String getToken() {
        return token;
    }
}

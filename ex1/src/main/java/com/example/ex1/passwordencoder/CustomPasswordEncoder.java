package com.example.ex1.passwordencoder;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        final var sb = new StringBuilder();

        for (int i = rawPassword.length() - 1; i >= 0; i--) {
            sb.append(rawPassword.charAt(i));
        }

        return sb.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }
}

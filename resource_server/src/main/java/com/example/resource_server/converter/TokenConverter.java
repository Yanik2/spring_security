package com.example.resource_server.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class TokenConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        final var authorities = (List<String>) source.getClaim("authorities");
        final var authentication = new JwtAuthenticationToken(source, authorities.stream().map(SimpleGrantedAuthority::new).toList());
        return authentication;
    }
}

package com.example.opaque_resource_server.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

import java.util.List;

public class CustomOpaqueTokenAuthConverter implements OpaqueTokenAuthenticationConverter {
    @Override
    public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {


        return new BearerTokenAuthentication(authenticatedPrincipal, null, List.of());
    }
}

package com.example.authorization_server.service;

import com.example.authorization_server.model.Client;
import com.example.authorization_server.repository.ClientRepository;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class CustomClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.findByClientId(registeredClient.getClientId())
                .ifPresentOrElse(client -> {
                    client.setScope(registeredClient.getScopes().stream().findAny().orElse("default_scope"));
                    client.setAuthMethod(registeredClient.getClientAuthenticationMethods()
                            .stream()
                            .findFirst()
                            .map(method -> method.getValue())
                            .orElse("default_authorization_method"));
                    client.setRedirectUri(registeredClient.getRedirectUris().stream().findFirst().orElse("default_uri"));
                    client.setGrantType(registeredClient.getAuthorizationGrantTypes()
                            .stream().findFirst()
                            .map(grantType -> grantType.getValue())
                            .orElse("default_grant_type"));
                    clientRepository.save(client);
                }, () -> {
                    final var newClient = new Client();
                    newClient.setClientId(registeredClient.getClientId());
                    newClient.setSecret(registeredClient.getClientSecret());
                    newClient.setScope(registeredClient.getScopes()
                            .stream().findFirst()
                            .orElse("default_scope"));
                    newClient.setAuthMethod(registeredClient.getClientAuthenticationMethods()
                            .stream().findFirst()
                            .map(method -> method.getValue())
                            .orElse("default_method"));
                    newClient.setRedirectUri(registeredClient.getRedirectUris()
                            .stream().findFirst()
                            .orElse("default_uri"));
                    newClient.setGrantType(registeredClient.getAuthorizationGrantTypes()
                            .stream().findFirst()
                            .map(grantType -> grantType.getValue())
                            .orElse("default_grant_type"));

                    clientRepository.save(newClient);
                });
    }

    @Override
    public RegisteredClient findById(String id) {
        final var client = clientRepository.findById(Integer.valueOf(id)).orElseThrow(RuntimeException::new);

        return RegisteredClient.withId(id)
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scope(client.getScope())
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .redirectUri(client.getRedirectUri())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofHours(24))
                        .build())
                .build();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        final var client = clientRepository.findByClientId(clientId).orElseThrow(RuntimeException::new);

        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .scope(client.getScope())
                .authorizationGrantType(new AuthorizationGrantType(client.getGrantType()))
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .redirectUri(client.getRedirectUri())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                        .accessTokenTimeToLive(Duration.ofHours(24))
                        .build())
                .build();
    }
}

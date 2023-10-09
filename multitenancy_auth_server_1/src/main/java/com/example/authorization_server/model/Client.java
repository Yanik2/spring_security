package com.example.authorization_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "secret")
    private String secret;
    @Column(name = "scope")
    private String scope;
    @Column(name = "auth_method")
    private String authMethod;
    @Column(name = "grant_type")
    private String grantType;
    @Column(name = "redirect_uri")
    private String redirectUri;
}

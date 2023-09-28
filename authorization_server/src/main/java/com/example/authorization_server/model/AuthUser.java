package com.example.authorization_server.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "authority")
    private String authority;
}

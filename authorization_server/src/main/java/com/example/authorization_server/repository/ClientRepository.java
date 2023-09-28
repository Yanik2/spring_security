package com.example.authorization_server.repository;

import com.example.authorization_server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByClientId(String clientId);
}

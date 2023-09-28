package com.example.authorization_server.repository;

import com.example.authorization_server.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    @Query("""
            select u from AuthUser u where u.username = :username
            """)
    Optional<AuthUser> findByUsername(String username);
}

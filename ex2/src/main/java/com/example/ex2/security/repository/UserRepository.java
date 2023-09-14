package com.example.ex2.security.repository;

import com.example.ex2.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from \"user\" where username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);
}

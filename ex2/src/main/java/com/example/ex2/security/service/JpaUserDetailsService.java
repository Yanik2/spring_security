package com.example.ex2.security.service;

import com.example.ex2.security.model.SecurityUser;
import com.example.ex2.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        final var authorities = user.getAuthorities();

        return new SecurityUser(user);
    }
}

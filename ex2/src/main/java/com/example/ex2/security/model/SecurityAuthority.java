package com.example.ex2.security.model;

import com.example.ex2.security.entity.AuthorityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final AuthorityEntity authorityEntity;
    @Override
    public String getAuthority() {
        return authorityEntity.getName();
    }
}

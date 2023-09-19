package com.example.method_security.security;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationCondition {
    public boolean decide() { // it can have parameters passed throw spel in @PreAuthorize or other annotations
        return true; //your logic
    }
}

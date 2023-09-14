package com.example.ex2.controller;

import com.example.ex2.security.model.SecurityUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo() {
        final var user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
    }
}

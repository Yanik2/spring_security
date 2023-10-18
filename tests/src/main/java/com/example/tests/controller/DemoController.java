package com.example.tests.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }

    @PostMapping("/demo")
    public String demoPost() {
        return "Demo Post";
    }
}

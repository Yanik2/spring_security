package com.example.csrf.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String demo(HttpServletRequest request) {
        return "index.html";
    }

    @PostMapping("/smth")
    public String doSomething() {
        System.out.println(";)");
        return "index.html";
    }
}

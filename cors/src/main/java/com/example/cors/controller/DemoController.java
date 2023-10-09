package com.example.cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
//    @CrossOrigin("http://localhost:8080")
    public String demo() {
        return "Demo!";
    }
}

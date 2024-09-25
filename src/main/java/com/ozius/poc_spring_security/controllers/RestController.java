package com.ozius.poc_spring_security.controllers;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }
}

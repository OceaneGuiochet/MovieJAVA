package com.ndroc.rocmovies.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public String sayHello( ) {
        return "hello world from my first controller !";
    }
    
}

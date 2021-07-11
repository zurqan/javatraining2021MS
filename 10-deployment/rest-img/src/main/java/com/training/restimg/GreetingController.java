package com.training.restimg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${spring.profiles.active:default}")
    private String test;

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name){
        return "Hello "+name +";"+test;
    }
}

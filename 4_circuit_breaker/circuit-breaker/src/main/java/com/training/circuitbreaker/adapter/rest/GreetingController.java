package com.training.circuitbreaker.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name){
        return sayHello(name);//cb .. name start with A 10 sec.. error -> default message "Hello!"
    }

    private String sayHello(String name) {
        return "Hello " + name;
    }


}

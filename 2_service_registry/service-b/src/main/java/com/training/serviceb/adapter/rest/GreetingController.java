package com.training.serviceb.adapter.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String greeting(){
        return "Hello World!";
    }

    @GetMapping("/by-name")
    public String greetingByName(@RequestParam String name){
        return "Hello "+name;
    }




}

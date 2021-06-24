package com.training.introductionstep2.adapter.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping("/{name}")
    public String sayHello(
            @PathVariable String name,
            @RequestParam(required = false) String location){
        return "Hello "+name +" in "+location;
    }


    @GetMapping("/ar/{name}")
    public String sayHelloInAr(
            @PathVariable String name,
            @RequestParam(required = false) String location){

        return "مرحبا ب "+name;
    }
}

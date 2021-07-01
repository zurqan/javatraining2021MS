package com.training.circuitbreaker.adapter.rest;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final CircuitBreakerFactory circuitBreakerFactory;

    public GreetingController(CircuitBreakerFactory circuitBreakerFactory) {
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name) {
//        return sayHello(name);//cb .. name start with A 10 sec.. error -> default message "Hello!"

        return circuitBreakerFactory
                .create("hello")
                .run(() -> sayHello(name),
                        th -> sayDefaultHello());
    }

    private String sayDefaultHello() {
        return "Hello!";
    }

    private String sayHello(String name) {

        if(name.startsWith("A")){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello " + name;
    }


}

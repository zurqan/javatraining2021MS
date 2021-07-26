package com.example.serviceA.adapter.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/greeting")
@Slf4j
public class GreetingController {

    private final RestTemplate restTemplate;

    private final Tracer tracer;

    public GreetingController(RestTemplate restTemplate, Tracer tracer) {
        this.restTemplate = restTemplate;
        this.tracer = tracer;
    }

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name){

        log.info("Hello message service a "+name);

        String bGreeting = restTemplate.getForObject("http://service-b/b-greeting/" + name, String.class);

        Span currentSpan = tracer.currentSpan();
        System.out.println("currentSpan = " + currentSpan);

        Span newSpan = tracer.nextSpan().name("child span");
        try(Tracer.SpanInScope spanInScope = tracer.withSpan(newSpan.start())){
            log.info("Hello message service a -- another span ; "+name);

        }finally {
            newSpan.end();
        }
        log.info("Hello message service a -- oriognal span "+name);

        return bGreeting;
    }
}

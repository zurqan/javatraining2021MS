package com.training.servicea.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("callb")
public class CallerController {

    private final RestTemplate restTemplate;

    @Value("${server.port:8080}")
    private int port;

    public CallerController(RestTemplate restTemplate) {

        System.out.println("CallerController.CallerController");
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String greeting(){
        System.out.println("port = " + port);
        String bRespo = restTemplate.getForObject("http://service-b/greeting", String.class);
        return "Response from b "+bRespo;
    }

    @GetMapping("/{name}")
    public String greetingByName(@PathVariable String name){
        String bRespo = restTemplate.getForObject("http://service-b/greeting/by-name?name={name}", String.class,name);
        return "Response from b "+bRespo;
    }
}

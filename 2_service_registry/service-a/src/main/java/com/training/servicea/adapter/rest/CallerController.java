package com.training.servicea.adapter.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("callb")
public class CallerController {

    private final RestTemplate restTemplate;

    public CallerController(RestTemplate restTemplate) {

        System.out.println("CallerController.CallerController");
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String greeting(){
        String bRespo = restTemplate.getForObject("http://service-b/greeting", String.class);
        return "Response from b "+bRespo;
    }
}

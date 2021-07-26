package com.example.serviceB.adapter.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b-greeting")
@Slf4j
public class BGreetingController {
//    final static Logger log = LoggerFactory.getLogger(BGreetingController.class.getSimpleName());

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name) {
        log.info("Service - b - greeting");

        return "Hello From B " + name;
    }
}

package com.training.reactivemysql.adapter.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class GreetingController {

    @GetMapping("/greeting/{name}")
    public Mono<String> greeting(@PathVariable String name) {
        return Mono.just("Hello " + name);
    }

    @GetMapping("/greetings/{name}")
    public ResponseEntity<Flux<String>> greetings(@PathVariable String name) {
        return
                ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(
                                Flux
                                        .fromStream(
                                                Stream.generate(() -> "Hello " + name))
                                        .delayElements(Duration.ofSeconds(1)
                                        ))
                ;
    }
}

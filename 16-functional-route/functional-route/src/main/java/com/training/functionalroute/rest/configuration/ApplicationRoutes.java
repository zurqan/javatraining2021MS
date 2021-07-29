package com.training.functionalroute.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

@Configuration
public class ApplicationRoutes {


    @Bean
    RouterFunction<ServerResponse> greetingAPIs() {
        return route()
                .GET("/greeting/{name}",
                        req -> req.pathVariable("name").startsWith("M")
                        ,
                        req -> ok().body("Hello M User " + req.pathVariable("name")))
                .GET("/greeting/{name}",
                        req -> ok().body("Hello " + req.pathVariable("name")))

                .GET("/greeting-ar/{name}",
                        req -> ok().body("مرحبا " + req.pathVariable("name")))
                .DELETE("/greeting/{name}",
                        request -> ok().body("Greeting was deleted"))
                .build();

    }
}

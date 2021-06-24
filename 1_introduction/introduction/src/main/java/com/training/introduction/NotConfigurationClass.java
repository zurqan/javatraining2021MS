package com.training.introduction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class NotConfigurationClass {


    @Bean
    public CommandLineRunner another(){
        return args->{
            System.out.println("Hello World 3");
        };
    }
}

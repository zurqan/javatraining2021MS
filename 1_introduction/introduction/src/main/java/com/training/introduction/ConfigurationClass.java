package com.training.introduction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
//@Component
public class ConfigurationClass {


    @Bean
    public CommandLineRunner another(){
        return args->{
            System.out.println("Hello World 4");
        };
    }

    @Bean
    public MangedCLR temp(){
        return new MangedCLR();
    }
}

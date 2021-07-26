package com.example.sampleconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleConfigApplication {

    @Value("${training.greeting:test}")
    private String greeting;
	public static void main(String[] args) {
		SpringApplication.run(SampleConfigApplication.class, args);
	}


	@Bean
    CommandLineRunner testVal(){

	    return args->{

            System.out.println("greeting = " + greeting);
        };
    }
}

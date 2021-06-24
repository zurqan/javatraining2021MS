package com.training.introduction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AnotherCLR implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Another Hello World!");
    }
}

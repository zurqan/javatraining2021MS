package com.training.introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GreetingCLR implements CommandLineRunner {

    private final GreetingMessage greetingMessage;

    public GreetingCLR( GreetingMessage greetingMessage) {

        this.greetingMessage = greetingMessage;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("greetingMessage.sayHello(\"Mohammad\") = " + greetingMessage.sayHello("Mohammad"));

    }


}

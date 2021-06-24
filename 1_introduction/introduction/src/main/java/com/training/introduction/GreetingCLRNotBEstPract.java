package com.training.introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GreetingCLRNotBEstPract implements CommandLineRunner {

    @Autowired
    private  GreetingMessage greetingMessage;

    public GreetingCLRNotBEstPract() {

    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("not recommended  version greetingMessage.sayHello(\"Mohammad\") = " + greetingMessage.sayHello("Mohammad"));

    }

}

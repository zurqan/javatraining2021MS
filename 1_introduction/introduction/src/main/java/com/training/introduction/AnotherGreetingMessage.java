package com.training.introduction;

import org.springframework.stereotype.Component;

@Component
public class AnotherGreetingMessage  extends GreetingMessage{

    public String sayHello(String name){
        return "مرحبا "+name;
    }
}

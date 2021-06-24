package com.training.introduction;

import org.springframework.stereotype.Component;

//@Component
public class GreetingMessage {

    public String sayHello(String name){
        return "Hello "+name;
    }
}

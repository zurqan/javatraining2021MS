package com.training.introduction.configuration;

import com.training.introduction.ArGreeting;
import com.training.introduction.Greeting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.training.messaging.ar",name = "enabled",matchIfMissing = true)
public class ArGreetingConfiguration {

    @Bean
    public Greeting greetingAr(){
        return new ArGreeting();
    }
}

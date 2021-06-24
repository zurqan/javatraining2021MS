package com.training.introduction.configuration;

import com.training.introduction.EngGreeting;
import com.training.introduction.Greeting;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "com.training.messaging.en",name = "enabled",matchIfMissing = false)
public class EngGreetingConfiguration {

    @Bean
//    @Bean("Greeting2")
    public Greeting greetingEn(){
        return new EngGreeting();
    }
}

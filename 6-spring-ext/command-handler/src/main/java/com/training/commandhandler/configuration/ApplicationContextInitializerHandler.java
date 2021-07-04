package com.training.commandhandler.configuration;

import com.training.commandhandler.util.CommandRegistration;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationContextInitializerHandler implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {


        applicationContext
                .getBeanFactory()
                .registerSingleton("commandRegistration",new CommandRegistration());
    }
}

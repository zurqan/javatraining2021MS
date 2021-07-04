package com.training.commandhandler.configuration;

import com.training.commandhandler.util.CommandBus;
import com.training.commandhandler.util.CommandRegistration;
import com.training.commandhandler.util.DefaultCommandBus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "com.training.command-handler",name = "enabled",matchIfMissing = true)
public class CommandBusAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CommandBus commandBus(CommandRegistration commandRegistration){
        System.out.println("####CommandBusAutoConfiguration.commandBus");
        return new DefaultCommandBus(commandRegistration);
    }
}

package com.training.usermanagement.commandhandler;

import org.springframework.stereotype.Component;

//@Component
public class DefaultCommandBus implements CommandBus {
    @Override
    public <U> U send(Command command) {
        return null;
    }
}

package com.training.pomang;

import com.training.commandhandler.util.Command;
import com.training.commandhandler.util.CommandBus;
import com.training.commandhandler.util.CommandRegistration;
import org.springframework.stereotype.Component;

//@Component
public class DummyCommandBus implements CommandBus {
    final CommandRegistration commandRegistration;
    public DummyCommandBus(CommandRegistration commandRegistration) {
        this.commandRegistration = commandRegistration;

        System.out.println("****DummyCommandBus.DummyCommandBus");
        System.out.println("commandRegistration.getClass() = " + commandRegistration.getClass());
    }

    @Override
    public <U> U send(Command command) {
        System.out.println("Do Nothing");
        return null;
    }
}

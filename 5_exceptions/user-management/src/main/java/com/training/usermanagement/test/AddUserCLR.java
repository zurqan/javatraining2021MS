package com.training.usermanagement.test;

import com.training.usermanagement.command.AddUserCommand;
import com.training.usermanagement.commandhandler.CommandBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AddUserCLR implements CommandLineRunner {

    private final CommandBus commandBus;

    public AddUserCLR(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void run(String... args) throws Exception {
        AddUserCommand admin = AddUserCommand
                .builder()
                .age(100)
                .name("Admin")
                .build();

        String id = commandBus.send(admin);
        System.out.println("id = " + id);
    }
}

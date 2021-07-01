package com.training.usermanagement.commandhandler;

import com.training.usermanagement.command.AddUserCommand;
import com.training.usermanagement.exception.CommandHandlerNotFoundForClass;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

//don't use it in prod
@Component
@Profile("dummy")
public class DummySimpleUserCommandBusImpl implements CommandBus {

    private final AddUserCommandHandler addUserCommandHandler;

    public DummySimpleUserCommandBusImpl(AddUserCommandHandler addUserCommandHandler) {
        this.addUserCommandHandler = addUserCommandHandler;
    }

    @Override
    public <U> U send(Command command) {
        if(command instanceof AddUserCommand){
            return (U)addUserCommandHandler.addingUser((AddUserCommand)command);
        }


        throw new CommandHandlerNotFoundForClass(command.getClass().getName());
    }
}

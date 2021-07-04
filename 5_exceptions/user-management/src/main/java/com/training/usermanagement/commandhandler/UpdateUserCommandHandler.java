package com.training.usermanagement.commandhandler;

import com.training.usermanagement.command.CommandHandler;
import com.training.usermanagement.command.UpdateUserCommand;
import com.training.usermanagement.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler {
    private final UserService userService;

    public UpdateUserCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @CommandHandler
    public String updateUser(UpdateUserCommand updateUserCommand){

        return updateUserCommand.getId();

    }
}

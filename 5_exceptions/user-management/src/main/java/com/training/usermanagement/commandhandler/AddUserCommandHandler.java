package com.training.usermanagement.commandhandler;

import com.training.usermanagement.command.AddUserCommand;
import com.training.usermanagement.command.CommandHandler;
import com.training.usermanagement.model.User;
import com.training.usermanagement.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddUserCommandHandler extends Command{

    private final UserService userService;

    public AddUserCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @CommandHandler
    public String addingUser(AddUserCommand addUserCommand){
        String id = UUID.randomUUID().toString();
        User user = new User(id, addUserCommand.getName(), addUserCommand.getAge());
        userService.register(user);
        return id;
    }
}

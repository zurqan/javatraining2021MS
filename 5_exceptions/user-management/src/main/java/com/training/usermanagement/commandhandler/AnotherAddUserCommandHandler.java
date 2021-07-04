package com.training.usermanagement.commandhandler;

import com.training.usermanagement.command.AddUserCommand;
import com.training.usermanagement.command.CommandHandler;
import com.training.usermanagement.model.User;
import com.training.usermanagement.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("dev")
@Primary
public class AnotherAddUserCommandHandler extends Command{

    private final UserService userService;

    public AnotherAddUserCommandHandler(UserService userService) {
        this.userService = userService;
    }

//    @CommandHandler
//    public String updateUser(UpdateUserCommand updateUserCommand){
//
//        return updateUserCommand.getId();
//
//    }

    @CommandHandler
    public String addingUser(AddUserCommand addUserCommand){
        System.out.println("AnotherAddUserCommandHandler.addingUser");
        String id = "DEV-"+UUID.randomUUID().toString();
        User user = new User(id, addUserCommand.getName(), addUserCommand.getAge());
        userService.register(user);
        return id;
    }
}

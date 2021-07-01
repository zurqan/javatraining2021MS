package com.training.usermanagement.adapter.rest;

import com.training.usermanagement.adapter.rest.dto.AddUserDTO;
import com.training.usermanagement.command.AddUserCommand;
import com.training.usermanagement.commandhandler.CommandBus;
import com.training.usermanagement.exception.UserNotFoundException;
import com.training.usermanagement.model.User;
import org.springframework.web.bind.annotation.*;
import com.training.usermanagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final CommandBus commandBus;

    public UserController(UserService userService, CommandBus commandBus) {
        this.userService = userService;
        this.commandBus = commandBus;
    }

    @GetMapping("/{userId}")
    public User gettingUser(@PathVariable String userId){
        System.out.println("UserController.gettingUser");
        return userService
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PostMapping
    public String addingUser(@RequestBody AddUserDTO addUserDTO){
        AddUserCommand addUserCommand = AddUserCommand.builder().age(addUserDTO.getAge()).name(addUserDTO.getName()).build();
        return commandBus.send(addUserCommand);
    }
}

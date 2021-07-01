package com.training.usermanagement.adapter.rest;

import com.training.usermanagement.exception.UserNotFoundException;
import com.training.usermanagement.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.usermanagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User gettingUser(@PathVariable String userId){
        System.out.println("UserController.gettingUser");
        return userService
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}

package com.training.cloudstreamusermang.adapter.rest;

import com.training.cloudstreamusermang.model.User;
import com.training.cloudstreamusermang.service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManagementService userManagementService;

    public UserController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void registering(@RequestBody User user){
        userManagementService.registerUser(user);

    }
}

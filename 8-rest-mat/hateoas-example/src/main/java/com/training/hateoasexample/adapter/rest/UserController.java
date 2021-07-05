package com.training.hateoasexample.adapter.rest;

import com.training.hateoasexample.model.User;
import com.training.hateoasexample.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> registeringUser(@RequestBody User user){
        User registedUser = userService.register(user);
        EntityModel<User> userEntityModel = EntityModel.of(user)

                .add(
                        linkTo(
                                methodOn(UserController.class).loadingUser(registedUser.getId()))
                                .withRel("view"))
                .add(
                        linkTo(
                                methodOn(UserController.class).unregister(registedUser.getId()))
                                .withRel("unregister")
                );

        return ResponseEntity.ok(userEntityModel);
    }

    @GetMapping("/{id}")
    public User loadingUser(@PathVariable String id){
        return userService.id(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @DeleteMapping("/{id}")
    public User unregister(@PathVariable String id){
        return userService.unregister(id);
    }
}

package com.training.session2.adapter.rest;

import com.training.session2.adapter.rest.dto.UserDTO;
import com.training.session2.model.User;
import com.training.session2.adaper.repository.UserManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserManagement userManagement;

    public UserController(UserManagement userManagement) {

        System.out.println("userManagement.getClass().getSimpleName() = " + userManagement.getClass().getSimpleName());
        this.userManagement = userManagement;
    }

    @GetMapping("/{id}")
    public UserDTO loadingUser(@PathVariable String id) {

        return userManagement
                .byId(id)
                .map(toUserDto())
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    private Function<User, UserDTO> toUserDto() {
        return user -> UserDTO
                .builder()
                .userAge(user.getAge())
                .birthDay(user.getBirthDay())
                .id(user.getId())
                .name(user.getFirstName()+" "+user.getLastName())
                .build();
    }


}

package com.training.serviceb.adapter.rest;

import com.training.serviceb.adapter.rest.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    List<UserDTO> users=new CopyOnWriteArrayList(){{
        add(new UserDTO("1000","Ahmad",20));
        add(new UserDTO("1001","Mohammad",30));
        add(new UserDTO("1003","Mosa",33));
    }};//use map instead

    @GetMapping("/{id}")
    public UserDTO loadingUser(@PathVariable String id){
        return users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not Fonded"));
    }
}

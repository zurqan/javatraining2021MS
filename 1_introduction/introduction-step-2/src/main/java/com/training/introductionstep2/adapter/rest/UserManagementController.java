package com.training.introductionstep2.adapter.rest;

import com.training.introductionstep2.DummyComp;
import com.training.introductionstep2.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserManagementController {

//    private final DummyComp dummyComp;
    List<User> users=new ArrayList(){{
        add(new User("1000","Mohammad", 40));
        add(new User("1001","Ahmad", 20));
    }};

//    public UserManagementController(DummyComp dummyComp) {
//        this.dummyComp = dummyComp;
//    }

    @GetMapping("{id}")
    public User loadingUser(@PathVariable String id){
//        System.out.println(dummyComp);
        User user = users
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        System.out.println("user = " + user);
        return user;
    }
}

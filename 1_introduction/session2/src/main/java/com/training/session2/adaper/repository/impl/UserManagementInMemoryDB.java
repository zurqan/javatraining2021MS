package com.training.session2.adaper.repository.impl;


import com.training.session2.adaper.repository.UserManagement;
import com.training.session2.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@Component
//@Profile("in-memory")
class UserManagementInMemoryDB implements UserManagement {

    Map<String, User> users = new HashMap() {{
        put("1000",
                User.builder()
                        .age(20)
                        .id("1000")
                        .firstName("Mohammad")
                        .lastName("Esa")
                        .build());
        put("1001", User.builder().age(30).id("1001").firstName("Ahmad").lastName("Qais").build());

    }};

    public Optional<User> byId(String id) {

        return Optional.ofNullable(users.get(id));
    }
}

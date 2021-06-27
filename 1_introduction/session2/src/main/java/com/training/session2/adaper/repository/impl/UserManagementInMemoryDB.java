package com.training.session2.adaper.repository.impl;


import com.training.session2.adaper.repository.UserManagement;
import com.training.session2.model.User;

import java.util.*;

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

    @Override
    public String save(User user) {
        String userId = UUID.randomUUID().toString();
        users.put(userId,user);
        return userId;
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean update(User user) {
        return users.computeIfPresent(user.getId(),(k,v)->user)!=null;
    }

    @Override
    public boolean remove(String userId) {
        return users.remove(userId)!=null;
    }
}

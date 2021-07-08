package com.training.cloudstreamusermang.adapter.repository;

import com.training.cloudstreamusermang.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserRepoInMemoryImpl implements UserRepository {

    Map<String,User> users=new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(),user);
    }
}

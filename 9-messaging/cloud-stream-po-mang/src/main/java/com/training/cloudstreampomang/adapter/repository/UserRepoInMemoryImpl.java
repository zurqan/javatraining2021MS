package com.training.cloudstreampomang.adapter.repository;

import com.training.cloudstreampomang.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepoInMemoryImpl implements UserProjectionRepo {
    Map<String,User> users = new ConcurrentHashMap<>();
    @Override
    public void save(User user) {
        System.out.printf( "saving user in po caching %s\n",user);
        users.put(user.getId(),user);
    }
}

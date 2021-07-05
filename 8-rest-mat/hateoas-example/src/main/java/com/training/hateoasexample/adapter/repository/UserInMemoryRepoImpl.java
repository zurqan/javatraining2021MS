package com.training.hateoasexample.adapter.repository;

import com.training.hateoasexample.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserInMemoryRepoImpl implements UserRepository {

    Map<String,User>  users = new ConcurrentHashMap<>();

    @Override
    public Optional<User> getById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
         users.put(user.getId(),user);
        return user;
    }

    @Override
    public User delete(String id) {
        return users.remove(id);
    }
}

package com.training.hateoasexample.adapter.repository;

import com.training.hateoasexample.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String id);

    User save(User user);

    User delete(String id);
}

package com.training.usermanagement.adapter.repository;

import com.training.usermanagement.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> getById(String userId);

    String save(User user);
}

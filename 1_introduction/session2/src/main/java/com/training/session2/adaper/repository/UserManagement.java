package com.training.session2.adaper.repository;

import com.training.session2.model.User;

import java.util.Optional;

public interface UserManagement {

    public Optional<User> byId(String id);
}

package com.training.session2.adaper.repository.impl;

import com.training.session2.adaper.repository.UserManagement;
import com.training.session2.model.User;

import java.util.Optional;

 class UserManagementsRDSImpl implements UserManagement {

    @Override
    public Optional<User> byId(String id) {

        //get user from DB
        return Optional.empty();
    }
}

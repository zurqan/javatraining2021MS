package com.training.session2.adaper.repository.impl;

import com.training.session2.adaper.repository.UserManagement;
import com.training.session2.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

 class UserManagementsRDSImpl implements UserManagement {

    @Override
    public Optional<User> byId(String id) {

        //get user from DB
        return Optional.empty();
    }

     @Override
     public String save(User user) {
         return null;
     }

     @Override
     public List<User> all() {
         return Collections.emptyList();
     }

     @Override
     public boolean update(User user) {
         return false;
     }

     @Override
     public boolean remove(String userId) {
         return false;
     }
 }

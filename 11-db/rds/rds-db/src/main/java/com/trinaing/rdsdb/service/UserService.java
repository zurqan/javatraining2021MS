package com.trinaing.rdsdb.service;

import com.trinaing.rdsdb.adapter.repository.UserRepository;
import com.trinaing.rdsdb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long registerUser( User user ){

        //do first check

        //do secoond check
        // persist data

        return userRepository.save(user);
    }

    public Optional<User> loadById(Long id){
        return userRepository.byId(id);
    }


    public Page<User> loadAll(Pageable pageable){
        return userRepository.loadUsers(pageable);
    }

    public void unregisterUser(Long id){
        userRepository.removeUser(id);
    }
}

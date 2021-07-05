package com.training.hateoasexample.service;

import com.training.hateoasexample.adapter.repository.UserRepository;
import com.training.hateoasexample.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> id(String id){
        return userRepository.getById(id);
    }

    public User register(User user){
        return userRepository.save(user);
    }
    public User unregister(String id){
        return userRepository.delete(id);
    }
}

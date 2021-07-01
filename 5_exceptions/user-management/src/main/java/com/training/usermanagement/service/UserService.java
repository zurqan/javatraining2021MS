package com.training.usermanagement.service;

import com.training.usermanagement.adapter.repository.UserRepository;
import com.training.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String userId){
        return userRepository.getById(userId);
    }

    public String register(User user){
        return userRepository.save(user);
    }
}

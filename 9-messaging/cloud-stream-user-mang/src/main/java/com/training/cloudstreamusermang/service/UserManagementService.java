package com.training.cloudstreamusermang.service;

import com.training.cloudstreamusermang.adapter.out.msg.UserEventChannel;
import com.training.cloudstreamusermang.adapter.repository.UserRepository;
import com.training.cloudstreamusermang.model.User;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(UserEventChannel.class)
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserEventChannel userEventChannel;

    public UserManagementService(UserRepository userRepository, UserEventChannel userEventChannel) {
        this.userRepository = userRepository;
        this.userEventChannel = userEventChannel;
    }

    public void registerUser(User user) {

        userRepository.save(user);

        userEventChannel
                .registered()
                .send(
                        MessageBuilder
                                .withPayload(user).build());

    }
}

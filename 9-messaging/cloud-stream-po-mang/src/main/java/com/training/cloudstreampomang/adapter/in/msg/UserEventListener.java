package com.training.cloudstreampomang.adapter.in.msg;

import com.training.cloudstreampomang.adapter.repository.UserProjectionRepo;
import com.training.cloudstreampomang.model.User;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(UserEventChannel.class)
public class UserEventListener {
    private final UserEventChannel userEventChannel;
    private final UserProjectionRepo userProjectionRepo;

    public UserEventListener(UserEventChannel userEventChannel, UserProjectionRepo userProjectionRepo) {
        this.userEventChannel = userEventChannel;
        this.userProjectionRepo = userProjectionRepo;
    }

//    @StreamListener(UserEventChannel.USER_REGISTERED_IN)
    public void userRegistered(User user){
        System.out.format("UserEventListener.userRegistered %s\n",user);
        userProjectionRepo.save(user);

    }
    @StreamListener(UserEventChannel.USER_REGISTERED_IN)
    public void userRegistered(Message<User> msg){
        User user = msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        headers.entrySet()
                .stream()
                .forEach(System.out::println);

        System.out.format("UserEventListener.userRegistered %s\n",user);
        userProjectionRepo.save(user);

    }
}

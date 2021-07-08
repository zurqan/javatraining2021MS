package com.training.cloudstreampomang.adapter.in.msg;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserEventChannel {

    public String USER_REGISTERED_IN="user-registered";

    @Input(USER_REGISTERED_IN)
    SubscribableChannel userRegistered();
}

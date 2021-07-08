package com.training.cloudstreamusermang.adapter.out.msg;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserEventChannel {

    public String OUT_CHANNEL="USER_REGISTERED_EVENT";

    @Output(OUT_CHANNEL)
    MessageChannel registered();
}

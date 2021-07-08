package com.training.cloudstreamex1.adapter.in.msgs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface HelloCloudNotInGroup {

    String INPUT_CHANNEL="hello-cloud-ex2";

    @Input(INPUT_CHANNEL)
    SubscribableChannel input();
}

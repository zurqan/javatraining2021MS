package com.training.cloudstreamex1.adapter.out.msgs;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutChannels {

    String OUT_PUT_CHANNEL="hello-cloud";

    @Output(OUT_PUT_CHANNEL)
    MessageChannel helloCLoud();

}

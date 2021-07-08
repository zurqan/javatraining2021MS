package com.training.cloudstreamex1;

import com.training.cloudstreamex1.adapter.out.msgs.OutChannels;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OutChannels.class)
@Profile("dev")
public class TestOutChannel implements CommandLineRunner {

    private final OutChannels outChannels;

    public TestOutChannel(OutChannels outChannels) {
        this.outChannels = outChannels;
    }

    @Override
    public void run(String... args) throws Exception {
        outChannels
                .helloCLoud()
                .send(MessageBuilder.withPayload("Hello Cloud").build());
    }
}

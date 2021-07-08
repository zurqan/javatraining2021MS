package com.training.cloudstreamex1;

import com.training.cloudstreamex1.adapter.in.msgs.HelloCloud;
import com.training.cloudstreamex1.adapter.in.msgs.HelloCloudNotInGroup;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({HelloCloud.class,HelloCloudNotInGroup.class})

public class TestInChannel {
    private final HelloCloud helloCloud;

    public TestInChannel(HelloCloud helloCloud) {
        this.helloCloud = helloCloud;
    }


    @StreamListener(HelloCloud.INPUT_CHANNEL)
    public void listener(String msg){
        System.out.println("msg = " + msg);
    }

    @StreamListener(HelloCloudNotInGroup.INPUT_CHANNEL)
    public void listener2(String msg){
        System.out.println("Out Of Group msg = " + msg);
    }
}

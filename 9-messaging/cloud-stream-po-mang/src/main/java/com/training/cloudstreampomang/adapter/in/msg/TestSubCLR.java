package com.training.cloudstreampomang.adapter.in.msg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.cloudstreampomang.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

//@Component
public class TestSubCLR implements CommandLineRunner {

    private final UserEventChannel userEventChannel;

    public TestSubCLR(UserEventChannel userEventChannel, ObjectMapper objectMapper) {
        this.userEventChannel = userEventChannel;
        this.objectMapper = objectMapper;
    }

    private final ObjectMapper objectMapper;
    @Override
    public void run(String... args) throws Exception {
        userEventChannel
                .userRegistered()
                .subscribe((Message<?> msg) -> {

                    System.out.println(objectMapper);
                    try {
                        String userStr = new String((byte[]) msg.getPayload(), "UTF-8");
                        System.out.println("msg.getPayload() = " + userStr);

                        User user = objectMapper.readValue(userStr, User.class);

                        System.out.println("***user = " + user);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
//                    User payload = (User)msg.getPayload();
//                    System.out.println("payload = " + payload);
                    System.out.println("msg = " + msg);
                });
    }
}

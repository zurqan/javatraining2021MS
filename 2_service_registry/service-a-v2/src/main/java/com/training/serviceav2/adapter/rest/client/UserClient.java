package com.training.serviceav2.adapter.rest.client;

import com.training.serviceav2.adapter.rest.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-b"
//        ,fallbackFactory = UserClientFallBackImpl.class
)
public interface UserClient {

    @GetMapping("/users/{id}")
    public User userInfo(@PathVariable String id);
}

package com.training.usermanagement.adapter.repository;

import com.training.usermanagement.exception.CanNotConnectToDataBase;
import com.training.usermanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserInMemoryRepositoryImpl implements UserRepository {

    Map<String,User> users= new HashMap(){{
        put("1001",new User("1001","Mohammad",35));
        put("1002",new User("1002","Ahmad",20));
        put("1003",new User("1003","Mosa",15));
        put("1004",new User("1004","Esa",50));
        put("1005",new User("1005","Qais",40));
    }};
    @Override
    public Optional<User> getById(String userId) {
        if(userId.startsWith("A")){
            throw new CanNotConnectToDataBase("Can not connect to DB..");
        }
        return Optional.ofNullable(users.get(userId));
    }
}

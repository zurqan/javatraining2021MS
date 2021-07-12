package com.trinaing.rdsdb;

import com.trinaing.rdsdb.adapter.repository.UserRepository;
import com.trinaing.rdsdb.model.Email;
import com.trinaing.rdsdb.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestEntity implements CommandLineRunner {

    private final UserRepository userRepository;

    public TestEntity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        long savedId = userRepository
                .save(User.builder()
                        .name("Mohammad")
                        .email(new Email("a2@a.com"))
                        .city("Amman")
                        .age(30)
                        .active(true)

                        .build());

        System.out.println("savedId = " + savedId);

        System.out.println("userRepository.byId(savedId) = " + userRepository.byId(savedId));

    }
}

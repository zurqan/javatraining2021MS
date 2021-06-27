package com.training.session2.adaper.repository.impl;

import com.training.session2.adaper.repository.UserManagement;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@ConditionalOnProperty
//@Profile("dev")
@Profile("in-memory")
public class InMemoryRepositoryConfiguration {

    @Bean
    public UserManagement inMemoryUM(){
        return new UserManagementInMemoryDB();
    }
}

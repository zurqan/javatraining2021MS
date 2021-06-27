package com.training.session2.adaper.repository.impl;

import com.training.session2.adaper.repository.UserManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
//@ConditionalOnProperty
//@Profile("prod")
@Profile("rds")

public class RDSRepositoryConfiguration {

    @Bean
    public UserManagement rdsUM(){
        return new UserManagementsRDSImpl();
    }
}

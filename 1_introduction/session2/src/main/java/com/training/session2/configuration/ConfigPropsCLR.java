package com.training.session2.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigPropsCLR implements CommandLineRunner {

    private final IntegrationConfigProps integrationConfigProps;

    public ConfigPropsCLR(IntegrationConfigProps integrationConfigProps) {
        this.integrationConfigProps = integrationConfigProps;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("integrationConfigProps = " + integrationConfigProps);
    }
}

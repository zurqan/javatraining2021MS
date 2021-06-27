package com.training.session2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.training.integration")
@Data
@Component
public class IntegrationConfigProps {

    private int port=9999;

    private int numberOfConnection=2;

    /**
     * Time out in milli-seconds
     */
    private long timeOut=50000;

    private String ip;
}

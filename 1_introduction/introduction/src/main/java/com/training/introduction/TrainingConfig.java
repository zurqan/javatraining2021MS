package com.training.introduction;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.training")
//@Component
public class TrainingConfig {
    private String message;
    private int connectionNo;
    private int port;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getConnectionNo() {
        return connectionNo;
    }

    public void setConnectionNo(int connectionNo) {
        this.connectionNo = connectionNo;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "TrainingConfig{" +
                "message='" + message + '\'' +
                ", connectionNo=" + connectionNo +
                ", port=" + port +
                '}';
    }
}

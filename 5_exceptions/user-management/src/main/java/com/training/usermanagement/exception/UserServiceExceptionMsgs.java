package com.training.usermanagement.exception;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "com.user.errors")
@Component
@Data
public class UserServiceExceptionMsgs {

    private Map<String,ErrorInfo> exceptionMessages;
}

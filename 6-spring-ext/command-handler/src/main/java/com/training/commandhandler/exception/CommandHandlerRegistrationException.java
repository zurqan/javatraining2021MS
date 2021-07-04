package com.training.commandhandler.exception;

import lombok.Getter;

@Getter
public class CommandHandlerRegistrationException extends RuntimeException {

    private final String methodName;
    private final Class<?> aClass;
    private final String beanName;

    public CommandHandlerRegistrationException(String msg, String methodName, Class<?> aClass, String beanName) {
        super(msg);
        this.methodName = methodName;
        this.aClass = aClass;
        this.beanName = beanName;
    }
}

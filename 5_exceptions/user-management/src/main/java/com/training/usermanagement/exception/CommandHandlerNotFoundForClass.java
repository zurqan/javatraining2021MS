package com.training.usermanagement.exception;

public class CommandHandlerNotFoundForClass extends AbstractUserServiceException {
    public CommandHandlerNotFoundForClass(String name) {
        super(name);
    }
}

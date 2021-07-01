package com.training.usermanagement.exception;

public abstract class AbstractUserServiceException  extends RuntimeException{

    public AbstractUserServiceException(String message) {
        super(message);
    }
}

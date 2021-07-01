package com.training.usermanagement.exception;

public class UserNotFoundException extends AbstractUserServiceException {

    private final String userId;

    public UserNotFoundException(String userId) {
        super(userId);
        this.userId = userId;
    }
}

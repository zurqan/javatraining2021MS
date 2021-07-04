package com.training.commandhandler.exception;

public class CommandHandlerNotFoundForClass extends RuntimeException {
    public CommandHandlerNotFoundForClass(String name) {
        super(name);
    }
}

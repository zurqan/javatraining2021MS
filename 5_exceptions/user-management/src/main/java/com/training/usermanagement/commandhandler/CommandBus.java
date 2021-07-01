package com.training.usermanagement.commandhandler;

public interface CommandBus {

    public <U> U send(Command command);
}

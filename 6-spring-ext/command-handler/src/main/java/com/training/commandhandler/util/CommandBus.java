package com.training.commandhandler.util;

public interface CommandBus {

    public <U> U send(Command command);
}

package com.training.usermanagement.command;

import com.training.usermanagement.commandhandler.Command;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserCommand extends Command {
    private String id;
    private String name;
    private int age;

}

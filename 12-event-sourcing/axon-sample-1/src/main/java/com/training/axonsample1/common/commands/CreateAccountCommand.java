package com.training.axonsample1.common.commands;

import com.training.axonsample1.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private String id;//-> ref to account aggregate

    private String name;

    private int initialBalance;

    private AccountType accountType;
}

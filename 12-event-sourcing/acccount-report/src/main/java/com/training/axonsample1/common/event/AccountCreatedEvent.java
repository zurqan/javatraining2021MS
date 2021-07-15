package com.training.axonsample1.common.event;

import com.training.axonsample1.model.AccountType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreatedEvent {

    private String accountId;

    private String name;

    private int initialBalance;

    private AccountType accountType;

}

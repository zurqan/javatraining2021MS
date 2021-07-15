package com.training.axonsample1.common.event;

import com.training.axonsample1.model.AccountType;
import lombok.Builder;
import lombok.Value;

@Value
public class AmountDepositedEvent {

    private String accountId;


    private int amount;


}

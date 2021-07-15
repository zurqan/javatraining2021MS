package com.training.axonsample1.common.event;

import lombok.Value;

@Value
public class AmountDepositedEvent {

    private String accountId;


    private int amount;


}

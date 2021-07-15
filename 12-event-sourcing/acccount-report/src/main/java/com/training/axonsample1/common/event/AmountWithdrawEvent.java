package com.training.axonsample1.common.event;

import lombok.Value;

@Value
public class AmountWithdrawEvent {

    private String accountId;


    private int amount;


}

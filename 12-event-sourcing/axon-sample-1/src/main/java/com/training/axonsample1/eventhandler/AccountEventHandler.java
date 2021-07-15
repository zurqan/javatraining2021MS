package com.training.axonsample1.eventhandler;

import com.training.axonsample1.common.event.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {

    @EventHandler
    public void accountCreated(AccountCreatedEvent accountCreatedEvent){
        System.out.println("accountCreatedEvent = " + accountCreatedEvent);
    }

    @EventHandler
    public void amountDeposited(AmountDepositedEvent amountDepositedEvent){
        System.out.println("amountDepositedEvent = [" + amountDepositedEvent + "]");
    }
    @EventHandler
    public void amountWithdraw(AmountWithdrawEvent amountWithdrawEvent){
        System.out.println("amountWithdrawEvent = [" + amountWithdrawEvent + "]");
    }
    @EventHandler
    public void accountActivated(AccountActivatedEvent accountActivatedEvent){
        System.out.println("accountActivatedEvent = [" + accountActivatedEvent + "]");
    }
    @EventHandler
    public void accountDeActivated(AccountInactivatedEvent accountInactivatedEvent){
        System.out.println("accountInactivatedEvent = [" + accountInactivatedEvent + "]");
    }
}

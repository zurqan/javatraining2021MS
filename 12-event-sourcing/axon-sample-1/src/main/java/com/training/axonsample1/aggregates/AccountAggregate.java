package com.training.axonsample1.aggregates;

import com.training.axonsample1.common.commands.*;
import com.training.axonsample1.common.event.*;
import com.training.axonsample1.model.AccountType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Profile("aggregate")
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private AccountType accountType;

    private String name;

    private int balance;

    private boolean active = true;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){

        AccountCreatedEvent accountCreatedEvent = AccountCreatedEvent
                .builder()
                .accountId(createAccountCommand.getId())
                .accountType(createAccountCommand.getAccountType())
                .initialBalance(createAccountCommand.getInitialBalance())
                .name(createAccountCommand.getName())
                .build();

        this.id=createAccountCommand.getId();
        apply(accountCreatedEvent);
    }

    @CommandHandler
    public void depositingAmount(DepositAmountCommand depositAmountCommand){
        if(!active){
            throw new RuntimeException("You can not deposit on inactive account");
        }

        apply(new AmountDepositedEvent(this.id,depositAmountCommand.getAmount()));
    }

    @CommandHandler
    public void withdrawAmount(WithdrawAmountCommand  withdrawAmountCommand){
        if(!active){
            throw new RuntimeException("You can not withdraw from inactive account");
        }

        if(this.balance<withdrawAmountCommand.getAmount()){
            throw new RuntimeException("No available enough balance "+this.balance);
        }

        apply(new AmountWithdrawEvent(this.id,withdrawAmountCommand.getAmount()));
    }

    @CommandHandler
    public void inactivatingAccount(InactivateAccountCommand inactivateAccountCommand){
        if(!this.active)return;

        apply(new AccountInactivatedEvent(this.id));
    }
    @CommandHandler
    public void activatingAccount(ActivateAccountCommand activateAccountCommand){
        if(this.active)return;

        apply(new AccountActivatedEvent(this.id));
    }

    @EventSourcingHandler
    public void accountCreated(AccountCreatedEvent accountCreatedEvent){
        System.out.println("accountCreatedEvent = " + accountCreatedEvent);
        this.id=accountCreatedEvent.getAccountId();
        this.balance=accountCreatedEvent.getInitialBalance();
        this.name=accountCreatedEvent.getName();
        this.accountType=accountCreatedEvent.getAccountType();

    }

    @EventSourcingHandler
    public void amountDeposited(AmountDepositedEvent amountDepositedEvent){
        System.out.println("amountDepositedEvent = " + amountDepositedEvent);
        System.out.println("old - this.balance = " + this.balance);
        this.balance+=amountDepositedEvent.getAmount();
        System.out.println("new - this.balance = " + this.balance);
    }

    @EventSourcingHandler
    public void amountWithdraw(AmountWithdrawEvent amountWithdrawEvent){
        System.out.println("amountWithdrawEvent = " + amountWithdrawEvent);
        System.out.println("old - this.balance = " + this.balance);
        this.balance-=amountWithdrawEvent.getAmount();
        System.out.println("new - this.balance = " + this.balance);
    }

    @EventSourcingHandler
    public void accountInactivated(AccountInactivatedEvent accountInactivatedEvent){
        System.out.println("accountInactivatedEvent = " + accountInactivatedEvent);
        this.active=false;
    }
    @EventSourcingHandler
    public void accountActivated(AccountActivatedEvent accountActivatedEvent){
        System.out.println("accountActivatedEvent = " + accountActivatedEvent);
        this.active=true;
    }

}

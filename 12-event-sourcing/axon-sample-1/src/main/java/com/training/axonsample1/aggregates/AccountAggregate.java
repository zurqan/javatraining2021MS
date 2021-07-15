package com.training.axonsample1.aggregates;

import com.training.axonsample1.common.commands.CreateAccountCommand;
import com.training.axonsample1.common.commands.DepositAmountCommand;
import com.training.axonsample1.common.event.AccountCreatedEvent;
import com.training.axonsample1.common.event.AmountDepositedEvent;
import com.training.axonsample1.model.AccountType;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
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

}

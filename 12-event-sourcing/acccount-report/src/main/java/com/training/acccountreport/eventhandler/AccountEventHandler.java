package com.training.acccountreport.eventhandler;

import com.training.acccountreport.adapter.repository.AccountReportRepository;
import com.training.axonsample1.common.event.AccountCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {
    private final AccountReportRepository acountReporrtRepository;

    public AccountEventHandler(AccountReportRepository accountReportRepository) {
        this.acountReporrtRepository = accountReportRepository;
    }

    @EventHandler // <-- subscribe topic AccountCreatedEvent
    //kafka ..rabbitmq topic name
    public void accountCreatedHandler(AccountCreatedEvent accountCreatedEvent){
        System.out.println("accountCreatedEvent = " + accountCreatedEvent);

        acountReporrtRepository.incrementNumberOfCreatedAccounts(accountCreatedEvent.getAccountType(),1);
    }
}

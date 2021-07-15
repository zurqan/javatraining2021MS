package com.training.acccountreport.adapter.repository.impl;

import com.training.acccountreport.adapter.repository.AccountReportRepository;
import com.training.acccountreport.view.AccountCreatedStatistics;
import com.training.axonsample1.model.AccountType;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AccountRepoImpl implements AccountReportRepository {
    AtomicInteger totalCount = new AtomicInteger();
    AtomicInteger totalCurrentCount = new AtomicInteger();
    AtomicInteger totalSavingCount = new AtomicInteger();
    AtomicInteger totalSalaryCount = new AtomicInteger();
    @Override
    public void incrementNumberOfCreatedAccounts(AccountType accountType, int delta) {
        totalCount.addAndGet(delta);
        switch (accountType){
            case SALARY:
                totalSalaryCount.addAndGet(delta);
                break;
            case SAVING:
                totalSavingCount.addAndGet(delta);
                break;
            case CURRENT:
                totalCurrentCount.addAndGet(delta);
                break;
        }
    }

    @Override
    public int getAccountCount() {
        return totalCount.get();
    }

    @Override
    public AccountCreatedStatistics getAccountStatistics() {
        return new AccountCreatedStatistics(totalSavingCount.get(),totalCurrentCount.get(),
                totalSalaryCount.get(),totalCount.get());
    }


}

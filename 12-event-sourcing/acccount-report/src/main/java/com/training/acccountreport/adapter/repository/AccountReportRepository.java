package com.training.acccountreport.adapter.repository;

import com.training.acccountreport.view.AccountCreatedStatistics;
import com.training.axonsample1.model.AccountType;

public interface AccountReportRepository {
    void incrementNumberOfCreatedAccounts(AccountType accountType, int delta);

    int getAccountCount();

    AccountCreatedStatistics getAccountStatistics();
}

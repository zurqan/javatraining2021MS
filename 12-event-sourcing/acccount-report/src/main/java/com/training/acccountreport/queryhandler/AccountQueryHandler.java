package com.training.acccountreport.queryhandler;

import com.training.acccountreport.adapter.repository.AccountReportRepository;
import com.training.acccountreport.view.AccountCreatedStatistics;
import com.training.axonsample1.common.query.TotalAccountCountByTypeQuery;
import com.training.axonsample1.common.query.TotalAccountCountQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountQueryHandler {

    private final AccountReportRepository accountReportRepository;

    public AccountQueryHandler(AccountReportRepository accountReportRepository) {
        this.accountReportRepository = accountReportRepository;
    }

    @QueryHandler
    public Integer getTotalAccountCount(TotalAccountCountQuery totalAccountCountQuery) {
        System.out.println("AccountQueryHandler.getTotalAccountCount");
        return accountReportRepository.getAccountCount();
    }

    @QueryHandler
    public Integer getTotalAccountCountByType(TotalAccountCountByTypeQuery totalAccountCountByTypeQuery) {
        System.out.println("AccountQueryHandler.getTotalAccountCount");
        AccountCreatedStatistics accountStatistics = accountReportRepository.getAccountStatistics();

        switch (totalAccountCountByTypeQuery.getAccountType()) {
            case SALARY:
                return accountStatistics.getSalaryCount();
            case SAVING:
                return accountStatistics.getSavingCount();
            case CURRENT:
                return accountStatistics.getCurrentCount();
        }

        return 0;

    }
}

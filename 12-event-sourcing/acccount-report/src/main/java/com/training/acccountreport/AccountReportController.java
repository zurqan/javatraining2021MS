package com.training.acccountreport;

import com.training.acccountreport.adapter.repository.AccountReportRepository;
import com.training.axonsample1.common.query.TotalAccountCountQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/accounts")
public class AccountReportController {


    private final QueryGateway queryGateway;

    public AccountReportController( QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/count")
    public Integer gettingTotalAccountCount() throws ExecutionException, InterruptedException {
        return queryGateway.query(new TotalAccountCountQuery(), Integer.class).get();
    }
}

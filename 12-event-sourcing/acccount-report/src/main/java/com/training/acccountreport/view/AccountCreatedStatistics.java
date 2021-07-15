package com.training.acccountreport.view;

import lombok.Data;
import lombok.Value;

@Value
public class AccountCreatedStatistics {
    private int savingCount;
    private int currentCount;
    private int salaryCount;
    private int total;

}

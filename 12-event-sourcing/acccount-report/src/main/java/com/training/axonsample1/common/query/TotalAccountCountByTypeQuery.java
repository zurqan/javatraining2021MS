package com.training.axonsample1.common.query;

import com.training.axonsample1.model.AccountType;
import lombok.Data;

@Data
public class TotalAccountCountByTypeQuery {

    private AccountType accountType;
}

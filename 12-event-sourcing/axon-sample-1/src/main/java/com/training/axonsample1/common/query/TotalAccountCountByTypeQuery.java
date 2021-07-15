package com.training.axonsample1.common.query;

import com.training.axonsample1.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAccountCountByTypeQuery {

    private AccountType accountType;
}

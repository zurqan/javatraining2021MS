package com.training.axonsample1.adapter.rest.dto;

import com.training.axonsample1.model.AccountType;
import lombok.Data;

@Data
public class CreatingAccountDTO {

    private String name;

    private AccountType accountType;

    private int initialBalance;
}

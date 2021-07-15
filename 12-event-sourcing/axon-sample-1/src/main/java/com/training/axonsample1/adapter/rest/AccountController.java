package com.training.axonsample1.adapter.rest;

import com.training.axonsample1.adapter.rest.dto.CreatingAccountDTO;
import com.training.axonsample1.common.commands.CreateAccountCommand;
import com.training.axonsample1.common.commands.DepositAmountCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CommandGateway commandGateway;

    public AccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }


    @PostMapping
    public String creatingAccount(CreatingAccountDTO creatingAccountDTO){
        CreateAccountCommand createAccountCommand = CreateAccountCommand
                .builder()
                .id(UUID.randomUUID().toString())
                .accountType(creatingAccountDTO.getAccountType())
                .name(creatingAccountDTO.getName())
                .initialBalance(creatingAccountDTO.getInitialBalance())
                .build();


        return commandGateway.sendAndWait(createAccountCommand);
    }

    @PatchMapping("/{id}/deposit")
    public void depositingAmount(DepositAmountCommand depositAmountCommand){
        commandGateway.send(depositAmountCommand);
    }

}

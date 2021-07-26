package com.training.axonsample1.adapter.rest;

import com.training.axonsample1.adapter.rest.dto.CreatingAccountDTO;
import com.training.axonsample1.common.commands.*;
import com.training.axonsample1.common.query.LoadNameQuery;
import com.training.axonsample1.common.query.TotalAccountCountByTypeQuery;
import com.training.axonsample1.common.query.TotalAccountCountQuery;
import com.training.axonsample1.model.AccountType;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    private final EventStore eventStore;

    public AccountController(CommandGateway commandGateway, QueryGateway queryGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }


    @PostMapping
    public String creatingAccount(@RequestBody CreatingAccountDTO creatingAccountDTO){
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
    public void depositingAmount( @RequestBody DepositAmountCommand depositAmountCommand){
        commandGateway.send(depositAmountCommand);
    }
    @PatchMapping("/{id}/withdraw")
    public void withdrawAmount(@RequestBody WithdrawAmountCommand withdrawAmountCommand){
        commandGateway.sendAndWait(withdrawAmountCommand);
    }

    @PatchMapping("/{id}/inactivate")
    public void inactivatingAccount(@PathVariable String id){
        commandGateway.send(new InactivateAccountCommand(id));
    }

    @PatchMapping("/{id}/activate")
    public void activatingAccount(@PathVariable String id){
        commandGateway.send(new ActivateAccountCommand(id));
    }

    @GetMapping("/count")
    public Integer gettingTotalAccountCount() throws ExecutionException, InterruptedException {
        return queryGateway.query(new TotalAccountCountQuery(), Integer.class).get();
    }
    @GetMapping("/{accountType}/count")
    public Integer gettingTotalAccountCount(@PathVariable AccountType accountType) throws ExecutionException, InterruptedException {
        return queryGateway.query(new TotalAccountCountByTypeQuery(accountType), Integer.class).get();
    }

    @GetMapping("/names")
    public List<String> loadNames() throws ExecutionException, InterruptedException {
        return queryGateway.query(new LoadNameQuery(), ResponseTypes.multipleInstancesOf(String.class)).get();
    }

    @GetMapping("/{id}/history")
    public LinkedList<? extends DomainEventMessage<?>> history(@PathVariable String id ){
        return eventStore
                .readEvents(id)
                .asStream()
                .peek(d-> System.out.println(d))
                .collect(Collectors.toCollection(LinkedList::new));
    }

}

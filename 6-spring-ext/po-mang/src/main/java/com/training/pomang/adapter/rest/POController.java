package com.training.pomang.adapter.rest;

import com.training.commandhandler.util.CommandBus;
import com.training.pomang.command.SubmitPOCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("pos")
public class POController {


    private final CommandBus commandBus;

    public POController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping
    public String submittingPO(@RequestBody SubmitPOCommand submitPOCommand){

        return null;
    }
}

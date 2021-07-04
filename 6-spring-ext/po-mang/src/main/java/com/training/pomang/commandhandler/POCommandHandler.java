package com.training.pomang.commandhandler;

import com.training.commandhandler.annotation.CommandHandler;
import com.training.pomang.command.SubmitPOCommand;
import org.springframework.stereotype.Component;

@Component
public class POCommandHandler {

    @CommandHandler
//    public String createPO(SubmitPOCommand submitPOCommand,String a){
    public String createPO(SubmitPOCommand submitPOCommand ){
        System.out.println("POCommandHandler.createPO");

        return "";
    }
}

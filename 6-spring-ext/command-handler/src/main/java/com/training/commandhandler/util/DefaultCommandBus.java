package com.training.commandhandler.util;

import com.training.commandhandler.annotation.CommandHandler;
import com.training.commandhandler.exception.CommandHandlerNotFoundForClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultCommandBus implements CommandBus, BeanPostProcessor
//, SmartLifecycle
{
    private final CommandRegistration commandRegistration;
    //Map < Command Class type, Tuple <Method related to the command, Bean of that method>>


    public DefaultCommandBus(CommandRegistration commandRegistration) {
        this.commandRegistration = commandRegistration;
    }

    @Override
    public <U> U send(Command command) {

        Tuple<Method, Object> methodObjectTuple = commandRegistration.get(command.getClass());//later check if sub-class is provided

        if(methodObjectTuple==null){
            throw new CommandHandlerNotFoundForClass(command.getClass().getName());
        }

        return (U)ReflectionUtils.invokeMethod(methodObjectTuple._1,methodObjectTuple._2,command);
    }


}

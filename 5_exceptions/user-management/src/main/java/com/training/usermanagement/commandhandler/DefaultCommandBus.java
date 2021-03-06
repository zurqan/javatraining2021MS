package com.training.usermanagement.commandhandler;

import com.training.usermanagement.command.CommandHandler;
import com.training.usermanagement.exception.CommandHandlerNotFoundForClass;
import com.training.usermanagement.util.Tuple;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultCommandBus implements CommandBus, BeanPostProcessor
//, SmartLifecycle
{
    //Map < Command Class type, Tuple <Method related to the command, Bean of that method>>
    private Map<Class, Tuple<Method,Object>> allHandlers = new ConcurrentHashMap<>();


    @Override
    public <U> U send(Command command) {

        Tuple<Method, Object> methodObjectTuple = allHandlers.get(command.getClass());//later check if sub-class is provided

        if(methodObjectTuple==null){
            throw new CommandHandlerNotFoundForClass(command.getClass().getName());
        }

        return (U)ReflectionUtils.invokeMethod(methodObjectTuple._1,methodObjectTuple._2,command);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //check if bean have method annotated with @CommmandHandler

        checkAndRegisterCommandHandler(bean,beanName);
        return bean;
    }

    private void checkAndRegisterCommandHandler(Object bean, String beanName) {

        System.out.println("beanName = " + beanName);
        ReflectionUtils
                .doWithMethods(bean.getClass(),(method -> {
                    //check each method in the bean class
                    CommandHandler declaredAnnotation = method.getDeclaredAnnotation(CommandHandler.class);
                    if(declaredAnnotation==null){
                        return;
                    }

                    //this method is a command handler method
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if(parameterTypes==null || parameterTypes.length>1){
                        return;
                    }

                    //this method have one parameter
                    Class<?> commandClassType = parameterTypes[0];
                    System.out.println("=============================");
                    System.out.println("bean.getClass().getSimpleName() = " + bean.getClass().getSimpleName());
                    System.out.println("beanName = " + beanName);
                    System.out.println("method.getName() = " + method.getName());
                    System.out.println("commandClassType = " + commandClassType);
                    System.out.println("=============================");

//                    allHandlers.put(AddUserCommad.class,Tuple <method of "addingUser", "AdduserCommandHandler bean")
                    allHandlers.put(commandClassType,new Tuple<>(method,bean));
                }));

    }
}

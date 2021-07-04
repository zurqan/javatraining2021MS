package com.training.commandhandler.util;

import com.training.commandhandler.annotation.CommandHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistration implements BeanPostProcessor {
    private Map<Class, Tuple<Method,Object>> allHandlers = new ConcurrentHashMap<>();

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



    public Tuple<Method, Object> get(Class<? extends Command> aClass) {
        return allHandlers.get(aClass);
    }
}

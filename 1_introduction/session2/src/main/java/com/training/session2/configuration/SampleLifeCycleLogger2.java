package com.training.session2.configuration;

import com.training.session2.common.util.Tuple;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class SampleLifeCycleLogger2 implements SmartLifecycle, ApplicationContextAware {
    private boolean started = false;
    private ApplicationContext applicationContext;

//    @PostConstruct
    public void test(){
        System.out.println("&&&&&&&&");
        Arrays.stream(applicationContext
                .getBeanDefinitionNames())
                .map(name->new Tuple(name,applicationContext.getBean(name)))
                .filter(t->t._2.getClass().getName().contains("com.training"))
                .forEach(System.out::println);
        System.out.println("&&&&&&&&");

    }
    @Override
    public void start() {
        System.out.println("*******************");
//        Arrays.stream(applicationContext
//                .getBeanDefinitionNames())
//                .map(name->new Tuple(name,applicationContext.getBean(name)))
//                .filter(t->t._2.getClass().getName().contains("com.training"))
//                .forEach(System.out::println);
        System.out.println("*******************");

        started = true;
    }

    @Override
    public void stop() {
            started=false;
    }


    @Override
    public boolean isRunning() {
        return started;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

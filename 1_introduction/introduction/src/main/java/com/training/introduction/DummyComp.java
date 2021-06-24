package com.training.introduction;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DummyComp implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println( "DummyComp created");
    }

    @PostConstruct
    public void init(){
        System.out.println("DummyComp.init");
    }
}

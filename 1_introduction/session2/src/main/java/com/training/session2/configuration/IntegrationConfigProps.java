package com.training.session2.configuration;

import com.training.session2.common.util.Tuple;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@ConfigurationProperties(prefix = "com.training.integration")
@Data
@Component
public class IntegrationConfigProps  implements ApplicationContextAware {

    private int port=9999;

    private int numberOfConnection=2;

    /**
     * Time out in milli-seconds
     */
    private long timeOut=50000;

    private String ip;

    private String responseTimeOut;
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

//    @PostConstruct
//    public void test(){
//        System.out.println("^^^^^^^");
//        Arrays.stream(applicationContext
//                .getBeanDefinitionNames())
//                .map(name->new Tuple(name,applicationContext.getBean(name)))
//                .filter(t->t._2.getClass().getName().contains("com.training"))
//                .forEach(System.out::println);
//        System.out.println("^^^^^^^");
//
//    }
}

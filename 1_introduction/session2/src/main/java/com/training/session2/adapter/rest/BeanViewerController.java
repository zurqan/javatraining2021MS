package com.training.session2.adapter.rest;

import com.training.session2.common.util.Tuple;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RestController
@RequestMapping("/bean-viewer")
public class BeanViewerController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @GetMapping("/{beanName}")
    public Object beanInfo(@PathVariable String beanName){

        return applicationContext.getBean(beanName);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


//    @PostConstruct
    public void test(){
        System.out.println("%%%%%%%%%%%%%%%%");
        Arrays.stream(applicationContext
                .getBeanDefinitionNames())
                .map(name->new Tuple(name,applicationContext.getBean(name)))
                .filter(t->t._2.getClass().getName().contains("com.training"))
                .forEach(System.out::println);
        System.out.println("%%%%%%%%%%%%%%%%");

    }
}

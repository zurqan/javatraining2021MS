package com.training.session2.adapter.rest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.training.cloudstreamhellofunc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class Operations {

    @Bean
    public Function<String,String> upper(){

        return s -> {
            System.out.format("received %s\n",s);
            return s.toUpperCase();
        };
    }
    @Bean
    public Function<String,String> up(){

        return s -> {
            System.out.format("received %s\n",s);
            return s.toUpperCase();
        };
    }
    @Bean
    public Function<String,String> per1(){

        return s -> {
            System.out.format("received %s\n",s);
            return s.toUpperCase();
        };
    }



    @Bean
    public Consumer<String> consume(){
        return s->{
            System.out.println("Consume Msg: "+s);
        };
    }
}

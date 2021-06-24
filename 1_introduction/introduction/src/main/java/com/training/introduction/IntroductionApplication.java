package com.training.introduction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties(TrainingConfig.class)
public class IntroductionApplication {

    @Value("${message}")
    private String greetingMessage;

    @Value("${com.training.message}")
    private String trainingMessage;

//    @Value("${message2}")
//    private String greetingMessage2;

    @Value("${message2:'Default Message'}")
    private String greetingMessage2;

    @Value("${connectionNo}")
    private int connectionNo;

    @Bean
    public CommandLineRunner readGreeting(){
        return args->{
            System.out.println("greetingMessage = " + greetingMessage);
            System.out.println("connectionNo = " + connectionNo);
            System.out.println("greetingMessage2 = " + greetingMessage2);
            System.out.println("trainingMessage = " + trainingMessage);
        };
    }

    @Bean
    public CommandLineRunner checkEnv(Environment env) {
        return args -> {
            String message = env.getProperty("message");
            System.out.println("message = " + message);

        };
    }

    @Bean
    public CommandLineRunner com1() {
        return args -> {
            System.out.println("Hello World!");
        };
    }

    @Bean
    public CommandLineRunner com2() {
        return args -> {
            System.out.println("Hello World 7!");
        };
    }


    @Bean
    public CommandLineRunner configuration(TrainingConfig config){
        return args->{
            System.out.println("config = " + config);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(IntroductionApplication.class, args);
    }

}

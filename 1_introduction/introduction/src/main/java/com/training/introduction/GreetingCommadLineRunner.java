package com.training.introduction;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GreetingCommadLineRunner implements CommandLineRunner {

//    private final Greeting greeting;
//
//    public GreetingCommadLineRunner(Greeting greeting) {
//        this.greeting = greeting;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("greeting.greeting(\"World\") = " + greeting.greeting("World"));
//    }

    private final Optional<Greeting> greeting;

    public GreetingCommadLineRunner(Optional<Greeting> greeting) {

        System.out.println("&&&&&greeting = " + greeting);
        this.greeting = greeting;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*******GreetingCommadLineRunner.run");
        greeting
                .map(g->g.greeting("Mohammad!!!"))
                .ifPresent(System.out::println);


        System.out.println("*******GreetingCommadLineRunner.run");

    }

//    private final List<Greeting> greetingList;
//
//    public GreetingCommadLineRunner(List<Greeting> greetingList) {
//        this.greetingList = greetingList;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        greetingList
//                .stream()
//                .map(g->g.greeting("Mohammad"))
//                .map(m->"*********\n"+m)
//                .forEach(System.out::println);
//    }
}

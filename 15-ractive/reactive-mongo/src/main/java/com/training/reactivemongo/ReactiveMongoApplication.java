package com.training.reactivemongo;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
public class ReactiveMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }


    @Bean
    CommandLineRunner firstExample() {

        return args -> {

            Flux<String> names = Flux
                    .just("Mohammad", "Ahmad", "esa")
                    .filter(name -> {
                        System.out.println("publisher: "+getThreadName());
                        return Character.isUpperCase(name.charAt(0));
                    })
                    .map(name -> {
                        System.out.println(Thread.currentThread().getName());
                        return "Hello " + name; })

//                    .subscribeOn(Schedulers.parallel());
//                    .publishOn(Schedulers.parallel())
                    .subscribeOn(Schedulers.single());


//            for (int i=0;i<40;i++) {
//                final int a = i;
//                names
//                        .subscribe(msg -> {
//                                    System.out.println("Sub" +a+": "+msg+getThreadName());
//
//                                    try {
//                                        Thread.sleep(2000);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//                                },
//                                thr -> thr.printStackTrace(),
//                                () -> {
//                                    System.out.println("Completed");
//                                });
//            }

            names

                    .subscribe(msg -> {
                                System.out.println("sub2 : "+msg+getThreadName());
                            },
                            thr -> thr.printStackTrace(),
                            () -> {
                                System.out.println("Completed");
                            });


            System.out.println("This therad "+getThreadName());
        };

    }

    @Bean
    CommandLineRunner secondExample(){
        return args->{

            Flux<Object> fib = Flux
                    .generate(() -> new Tuple<>(0L, 1L),
                    (state, sink) -> {
                        sink.next(state._1);
                        return new Tuple<>(state._2, state._1 + state._2);
                    });


            fib
                    .doOnRequest(reqLength-> System.out.println("reqLength: "+reqLength))
                    .doOnSubscribe(subc->{
                        subc.request(10);
                        subc.request(10);
                        subc.request(10);

                    })
                    .take(50)
                    .subscribe(a-> System.out.println(a));
        };
    }

    @ToString
    public class Tuple<A,B>{
        public final A _1;
        public final B _2;

        public Tuple(A a, B b) {
            _1 = a;
            _2 = b;
        }

    }

    private String getThreadName() {
        return " "+Thread.currentThread().getName();
    }
}

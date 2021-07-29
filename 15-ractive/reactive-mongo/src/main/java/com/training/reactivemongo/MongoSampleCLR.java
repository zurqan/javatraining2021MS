package com.training.reactivemongo;

import com.training.reactivemongo.adapter.repository.PersonMongoRepo;
import com.training.reactivemongo.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class MongoSampleCLR implements CommandLineRunner {

    private final PersonMongoRepo personMongoRepo;

    public MongoSampleCLR(PersonMongoRepo personMongoRepo) {
        this.personMongoRepo = personMongoRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Flux<Person> personFlux = Flux
                .just("Mohammad", "Ahmad", "Esa")
                .map(name -> {
                    System.out.println(" publisher Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    return new Person(null, name);
                })
                .flatMap(personMongoRepo::save);




//        personFlux
//                .subscribe(p->{
//                    System.out.println(" mongodb Thread.currentThread().getName() = " + Thread.currentThread().getName());
//
//                    System.out.println(p);
//                });

        personMongoRepo
                .deleteAll()
                .thenMany(personFlux)
                .thenMany(personMongoRepo.findAll())
//                .thenMany(personMongoRepo.findByName("Mohammad"))
                .subscribe(p-> System.out.println("p = " + p));


//        personMongoRepo
//                .findByName("Mohammad")
//                .subscribe(p-> System.out.println("p3 = " + p));
    }
}

package com.training.reactivemysql;

import com.training.reactivemysql.adapter.reepository.PersonRepoRDSImpl;
import com.training.reactivemysql.model.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMysqlApplication.class, args);
	}



	@Bean
    CommandLineRunner db(PersonRepoRDSImpl personRepoRDS){

	    return args->{
            Flux<Person> personFlux = Flux
                    .just("Mohammad", "Ahmad", "Esa")
                    .map(name -> {
                        System.out.println(" publisher Thread.currentThread().getName() = " + Thread.currentThread().getName());
                        return new Person(null, name);
                    })
                    .flatMap(personRepoRDS::save);


            personRepoRDS
                    .deleteAll()
                    .thenMany(personFlux)
                    .thenMany(personRepoRDS.findAll())
//                .thenMany(personMongoRepo.findByName("Mohammad"))
                    .subscribe(p-> System.out.println("p = " + p));
        };
    }
}

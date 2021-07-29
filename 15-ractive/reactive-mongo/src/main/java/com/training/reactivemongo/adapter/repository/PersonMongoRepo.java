package com.training.reactivemongo.adapter.repository;

import com.training.reactivemongo.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface PersonMongoRepo extends ReactiveMongoRepository<Person,String>{


    Flux<Person> findByName(String name);
}

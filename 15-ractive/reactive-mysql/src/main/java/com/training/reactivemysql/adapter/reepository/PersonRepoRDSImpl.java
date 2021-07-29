package com.training.reactivemysql.adapter.reepository;

import com.training.reactivemysql.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepoRDSImpl extends ReactiveCrudRepository<Person,Integer> {
}

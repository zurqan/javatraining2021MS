package com.training.mongosample.adapter.repository.impl.mongo;

import com.training.mongosample.adapter.repository.impl.mongo.documents.ItemCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepositoryMangoImpl extends MongoRepository<ItemCollection,String> {
}

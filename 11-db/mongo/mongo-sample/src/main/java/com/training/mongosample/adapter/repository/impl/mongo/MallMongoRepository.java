package com.training.mongosample.adapter.repository.impl.mongo;

import com.training.mongosample.adapter.repository.impl.mongo.documents.Mall;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface MallMongoRepository extends MongoRepository<Mall, BigInteger> {

    List<Mall> findByName (String name);

    List<Mall> findByNameContainingIgnoreCase(String subName);


    GeoResults<Mall> findByPositionNear(Point point, Distance distance);

}

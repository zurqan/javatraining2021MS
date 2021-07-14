package com.training.mongosample.test;

import com.training.mongosample.adapter.repository.impl.mongo.MallMongoRepository;
import com.training.mongosample.adapter.repository.impl.mongo.documents.Mall;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MallTest implements CommandLineRunner {
    private final MallMongoRepository mallMongoRepository;
    private final MongoTemplate mongoTemplate;
    Point AMMAN = new Point(31.963158,35.930359);
    Point MAAN = new Point(30.192663,35.724941);
    Point IRBID = new Point(    32.551445,35.851479);
    Point ALJAFER = new Point(30.317547,36.175076);

    Distance distance = new Distance(80, Metrics.KILOMETERS);

    public MallTest(MallMongoRepository mallMongoRepository, MongoTemplate mongoTemplate) {
        this.mallMongoRepository = mallMongoRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        mallMongoRepository.deleteAll();
        Mall gardenz = new Mall("Gardenz", 100, AMMAN);
        Mall maanMall = new Mall("Ma'an Mall", 200, MAAN);
        Mall irbidMall = new Mall("Irbid Mall", 500, IRBID);
        Mall aljaferMall = new Mall("aljafer mall", 400, ALJAFER);

        mongoTemplate.indexOps(Mall.class).ensureIndex(new GeospatialIndex("position"));

        mallMongoRepository.save(gardenz);
        mallMongoRepository.save(maanMall);
        mallMongoRepository.save(irbidMall);
        mallMongoRepository.save(aljaferMall);



        System.out.println("mallMongoRepository.findByName(\"Gardenz\") = " + mallMongoRepository.findByName("Gardenz"));

        System.out.println("mallMongoRepository.findByNameContainingIgnoreCase(\"mal\") = ") ;
    mallMongoRepository.findByNameContainingIgnoreCase("mal").forEach(System.out::println);

        System.out.println("mallMongoRepository.findByPositionNear(AMMAN,distance)");
        mallMongoRepository.findByPositionNear(AMMAN,distance)
                .forEach(System.out::println);


        Query query = new Query(Criteria.where("name").is("Gardenz"));
        List<Mall> malls = mongoTemplate.find(query, Mall.class);
        System.out.println("malls = " + malls);

    }
}

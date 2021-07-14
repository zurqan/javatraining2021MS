package com.training.mongosample.adapter.repository.impl.mongo.documents;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.math.BigInteger;

@Getter
@ToString
@Setter
public class Mall {


    @Id
    private BigInteger id;

    private String name;

    private int building;

//    @GeoSpatialIndexed(name="position")
    private Point position;

    public Mall() {
    }

    public Mall(String name, int building, Point position) {
        this.name = name;
        this.building = building;
        this.position = position;
    }
}

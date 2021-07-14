package com.training.mongosample.adapter.repository.impl.mongo.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemCollection {

    @Id
    private String id;

    @Field("pr")
    private int price;

    @Field("ld")
    private String longDescription;

    @Field("sd")
    private String shortDescription;

    @Field("mn")
    private String manName;

    @Field("mp")
    private String manPhone;

    @Field("qt")
    private int qty;
}

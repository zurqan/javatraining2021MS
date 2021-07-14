package com.training.mongosample.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Item {

    private String id;

    private int price;

    private String longDescription;

    private String shortDescription;

    private String manName;

    private String manPhone;

    private int qty;
}

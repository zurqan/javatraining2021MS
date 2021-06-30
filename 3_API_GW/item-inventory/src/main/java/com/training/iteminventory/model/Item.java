package com.training.iteminventory.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private String itemId;

    private double price;

    private int qty;

    private String name;

    private String shortDescription;

    private String fullDescription;

    private String imgUrl;

    private String manfId;


}

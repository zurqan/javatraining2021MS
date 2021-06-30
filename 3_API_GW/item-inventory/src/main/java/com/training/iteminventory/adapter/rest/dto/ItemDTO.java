package com.training.iteminventory.adapter.rest.dto;

import lombok.Data;

@Data
public class ItemDTO {

    private String itemId;

    private double price;

    private int qty;

    private String name;

    private String shortDescription;

    private String fullDescription;

    private String imgUrl;

    private String manfId;


}

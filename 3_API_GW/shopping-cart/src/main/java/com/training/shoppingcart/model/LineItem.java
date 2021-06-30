package com.training.shoppingcart.model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class LineItem {

    private String itemId;
    private int qty;
    private String shortDescription;
    private double price;

}

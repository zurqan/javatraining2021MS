package com.training.shoppingcart.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder(toBuilder = true)
@Getter
public class ShoppingCart {

    private String cartId;

    private String userId;

    private LocalDate createdDate;

    private List<LineItem> lineItems;



}

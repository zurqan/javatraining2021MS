package com.training.shoppingcart.adapter.rest.out.impl;

import com.training.shoppingcart.model.InventoryItem;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-inventory")
public interface InventoryItemFeignClient {


    public InventoryItem loadItem(String itemId);
}

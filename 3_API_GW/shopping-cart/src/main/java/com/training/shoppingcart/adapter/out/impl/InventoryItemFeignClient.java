package com.training.shoppingcart.adapter.out.impl;

import com.training.shoppingcart.model.InventoryItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "item-inventory")
public interface InventoryItemFeignClient {


    @GetMapping("/items/{itemId}")
    public InventoryItem loadItem(@PathVariable String itemId);
}

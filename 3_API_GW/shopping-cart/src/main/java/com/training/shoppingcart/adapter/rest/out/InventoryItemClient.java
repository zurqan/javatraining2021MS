package com.training.shoppingcart.adapter.rest.out;

import com.training.shoppingcart.model.InventoryItem;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-inventory")
public interface InventoryItemClient {


    public InventoryItem loadItem(String itemId);
}

package com.training.shoppingcart.adapter.rest.out.impl;

import com.training.shoppingcart.adapter.rest.out.InventoryItemClient;
import com.training.shoppingcart.model.InventoryItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("actual-inv")
public class FeignClientImpl implements InventoryItemClient {

    private final InventoryItemFeignClient inventoryItemFeignClient;

    public FeignClientImpl(InventoryItemFeignClient inventoryItemFeignClient) {
        this.inventoryItemFeignClient = inventoryItemFeignClient;
    }

    @Override
    public InventoryItem loadItem(String itemId) {
        return inventoryItemFeignClient
                .loadItem(itemId);
    }
}

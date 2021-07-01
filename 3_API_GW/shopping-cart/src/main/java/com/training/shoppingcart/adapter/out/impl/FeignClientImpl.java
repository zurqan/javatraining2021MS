package com.training.shoppingcart.adapter.out.impl;

import com.training.shoppingcart.adapter.out.InventoryItemClient;
import com.training.shoppingcart.model.InventoryItem;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("actual-inv")
public class FeignClientImpl implements InventoryItemClient {

    private final InventoryItemFeignClient inventoryItemFeignClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public FeignClientImpl(InventoryItemFeignClient inventoryItemFeignClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.inventoryItemFeignClient = inventoryItemFeignClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @Override
    public InventoryItem loadItem(String itemId) {
        return circuitBreakerFactory
                .create("cb-inv-item")
                .run(() -> inventoryItemFeignClient
                        .loadItem(itemId));

    }
}

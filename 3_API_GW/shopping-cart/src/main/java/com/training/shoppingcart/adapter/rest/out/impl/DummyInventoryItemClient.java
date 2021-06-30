package com.training.shoppingcart.adapter.rest.out.impl;

import com.training.shoppingcart.adapter.rest.out.InventoryItemClient;
import com.training.shoppingcart.model.InventoryItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dummy-inv")
public class DummyInventoryItemClient implements InventoryItemClient {
    List<InventoryItem> inventoryItems = new ArrayList(){{

        add(new InventoryItem("1000","Short desc 1000",100.0));
        add(new InventoryItem("1001","Short desc 1001",50.5));
        add(new InventoryItem("1002","Short desc 1002",233.2));
    }};

    @Override
    public InventoryItem loadItem(String itemId) {
        return inventoryItems
                .stream()
                .filter(i->i.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }
}

package com.training.shoppingcart.adapter.out;

import com.training.shoppingcart.model.InventoryItem;

public interface InventoryItemClient {


    public InventoryItem loadItem(String itemId);
}

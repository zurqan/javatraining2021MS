package com.training.iteminventory.adapter.repository.impl;

import com.training.iteminventory.adapter.repository.ItemRepository;
import com.training.iteminventory.model.Item;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ItemRepositoryInMemoryImpl implements ItemRepository {

    Map<String, Item> items = new HashMap() {{

        put("1001"
                , Item
                        .builder()
                        .itemId("1001")
                        .fullDescription("Full desc")
                        .shortDescription("Short")
                        .imgUrl("http://a.b/")
                        .price(100.3)
                        .name("Item name")
                        .manfId("Manf 123")
                        .qty(100)
                        .build());
        put("1002"
                , Item
                        .builder()
                        .itemId("1002")
                        .fullDescription("Full desc1002")
                        .shortDescription("Short 1002")
                        .imgUrl("http://a.b/1002")
                        .price(23.23)
                        .name("Item name 1002")
                        .manfId("Manf 123")
                        .qty(20)
                        .build());

    }};

    @Override
    public Optional<Item> getItemById(String itemId) {

        return Optional.ofNullable(items.get(itemId));
    }
}

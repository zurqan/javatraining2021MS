package com.training.iteminventory.service;

import com.training.iteminventory.adapter.repository.ItemRepository;
import com.training.iteminventory.model.Item;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> getItem(String itemId){
        return itemRepository
                .getItemById(itemId);
    }
}

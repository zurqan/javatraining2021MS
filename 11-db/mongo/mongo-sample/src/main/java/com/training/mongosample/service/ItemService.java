package com.training.mongosample.service;

import com.training.mongosample.adapter.repository.ItemRepository;
import com.training.mongosample.model.Item;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public Optional<Item> loadById(String id){
        return itemRepository.loadById(id);
    }
}

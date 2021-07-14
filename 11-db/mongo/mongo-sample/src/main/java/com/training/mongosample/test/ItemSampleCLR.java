package com.training.mongosample.test;

import com.training.mongosample.adapter.repository.ItemRepository;
import com.training.mongosample.model.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component
public class ItemSampleCLR implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public ItemSampleCLR(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        itemRepository.deleteAllItems();
        Item item1 = Item.builder()
                .id(UUID.randomUUID().toString())
                .longDescription("Long description")
                .shortDescription("Short Description")
                .price(100)
                .manName("Man Name value")
                .manPhone("+98876767")
                .qty(1300)
                .build();

        itemRepository.save(item1);

        System.out.println("itemRepository.loadById(item1.getId()) = " + itemRepository.loadById(item1.getId()));
    }
}

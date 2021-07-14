package com.training.mongosample.adapter.repository;

import com.training.mongosample.model.Item;

import java.util.Optional;

public interface ItemRepository {
    public void save(Item item) ;

    Optional<Item> loadById(String id);

    void deleteAllItems();
}

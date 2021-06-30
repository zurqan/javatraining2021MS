package com.training.iteminventory.adapter.repository;

import com.training.iteminventory.model.Item;

import java.util.Optional;

public interface ItemRepository {

    public Optional<Item> getItemById(String itemId);
}

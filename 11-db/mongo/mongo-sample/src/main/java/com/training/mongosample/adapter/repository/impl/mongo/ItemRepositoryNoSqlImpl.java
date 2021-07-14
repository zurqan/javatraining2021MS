package com.training.mongosample.adapter.repository.impl.mongo;


import com.training.mongosample.adapter.repository.ItemRepository;
import com.training.mongosample.adapter.repository.impl.mongo.documents.ItemCollection;
import com.training.mongosample.model.Item;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class ItemRepositoryNoSqlImpl implements ItemRepository {
    private final ItemRepositoryMangoImpl itemRepositoryMango;

    public ItemRepositoryNoSqlImpl(ItemRepositoryMangoImpl itemRepositoryMango) {
        this.itemRepositoryMango = itemRepositoryMango;
    }

    @Override
    public void save(Item item) {
        itemRepositoryMango.save(toCollection(item));
    }

    @Override
    public Optional<Item> loadById(String id) {
        return itemRepositoryMango
                .findById(id)
                .map(toItemModel());
    }

    @Override
    public void deleteAllItems() {
        itemRepositoryMango.deleteAll();
    }

    private Function<ItemCollection, Item> toItemModel() {

        return item -> Item
                .builder()
                .id(item.getId())
                .longDescription(item.getLongDescription())
                .shortDescription(item.getShortDescription())
                .manName(item.getManName())
                .manPhone(item.getManPhone())
                .qty(item.getQty())
                .build();
    }

    private ItemCollection toCollection(Item item) {

        return ItemCollection
                .builder()
                .id(item.getId())
                .longDescription(item.getLongDescription())
                .shortDescription(item.getShortDescription())
                .manName(item.getManName())
                .manPhone(item.getManPhone())
                .qty(item.getQty())
                .build();
    }
}

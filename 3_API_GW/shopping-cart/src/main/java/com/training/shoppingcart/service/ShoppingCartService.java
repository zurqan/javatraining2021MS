package com.training.shoppingcart.service;

import com.training.shoppingcart.adapter.repository.ShoppingCartRepo;
import com.training.shoppingcart.adapter.rest.out.InventoryItemClient;
import com.training.shoppingcart.model.InventoryItem;
import com.training.shoppingcart.model.LineItem;
import com.training.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;
    private final InventoryItemClient inventoryItemClient;


    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, InventoryItemClient inventoryItemClient) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.inventoryItemClient = inventoryItemClient;
    }

    public String openShoppingCart(String userId){

        String id= UUID.randomUUID().toString();

        ShoppingCart shoppingCart = ShoppingCart.builder().userId(userId).cartId(id).createdDate(LocalDate.now()).build();

        shoppingCartRepo.save(shoppingCart);

        return id;
    }

    public void addItem(String cartId,String userId,String itemId,int qty){

        Optional<ShoppingCart> shoppingCartOpt=shoppingCartRepo.getById(cartId);

        ShoppingCart shoppingCart = shoppingCartOpt
                .filter(cart -> cart.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException(String.format("No Cart with id %s for user %s", cartId, userId)));

        LineItem newLineItem = shoppingCart
                .getLineItems()
                .stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .map(lineItem -> lineItem.toBuilder().qty(lineItem.getQty()+qty).build())
                .orElseGet(() -> {

                    InventoryItem inventoryItem = inventoryItemClient.loadItem(itemId);
                    return LineItem.builder().itemId(itemId).qty(qty).price(inventoryItem.getPrice()).shortDescription(inventoryItem.getShortDescription()).build();
                });


        List<LineItem> newItemList = Stream.concat(Stream.of(newLineItem), shoppingCart
                .getLineItems()
                .stream()
                .filter(item -> !item.getItemId().equals(itemId))).collect(Collectors.toList());

        ShoppingCart newShoppingCart = shoppingCart.toBuilder().lineItems(newItemList).build();

        shoppingCartRepo.update(newShoppingCart);
    }

    public void updateItemQty(String cartId,String userId,String ItemId, int qry){

    }

    public void removeItem(String cartId,String userId,String itemId){

    }

    public ShoppingCart view(String cartId, String userId){

        return null;
    }
}

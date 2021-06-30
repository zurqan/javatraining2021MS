package com.training.shoppingcart.service;

import com.training.shoppingcart.adapter.repository.ShoppingCartRepo;
import com.training.shoppingcart.adapter.out.InventoryItemClient;
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

import static java.util.Collections.emptyList;

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

        ShoppingCart shoppingCart = ShoppingCart.builder().userId(userId).cartId(id).createdDate(LocalDate.now()).lineItems(emptyList()).build();

        shoppingCartRepo.save(shoppingCart);

        return id;
    }

    public void addItem(String cartId,String userId,String itemId,int qty){


        ShoppingCart shoppingCart = loadCart(cartId,userId);

        LineItem newLineItem = updateLineItemOpt(itemId, qty, shoppingCart,false)
                .orElseGet(() -> {

                    InventoryItem inventoryItem = inventoryItemClient.loadItem(itemId);
                    return LineItem.builder().itemId(itemId).qty(qty).price(inventoryItem.getPrice()).shortDescription(inventoryItem.getShortDescription()).build();
                });


        updateItem(shoppingCart, newLineItem);
    }

    public void updateItemQty(String cartId,String userId,String itemId, int qty){
        ShoppingCart shoppingCart = loadCart(cartId, userId);
        LineItem newLineItem = updateLineItemOpt(itemId, qty, shoppingCart,true)
                .orElseThrow(()-> new RuntimeException(String.format("No Item with id %s in cart %s", itemId,cartId)));

        updateItem(shoppingCart, newLineItem);


    }

    public void removeItem(String cartId,String userId,String itemId){
        ShoppingCart shoppingCart = loadCart(cartId, userId);
        List<LineItem> newLineItems = shoppingCart.getLineItems()
                .stream()
                .filter(i -> !i.getItemId().equals(itemId))
                .collect(Collectors.toList());

        ShoppingCart updatedShoppingCart = shoppingCart.toBuilder().lineItems(newLineItems).build();
        shoppingCartRepo.update(updatedShoppingCart);

    }

    public ShoppingCart view(String cartId, String userId){

        return loadCart(cartId, userId);
    }

    private Optional<LineItem> updateLineItemOpt(String itemId, int qty, ShoppingCart shoppingCart,boolean replace) {
        return shoppingCart
                .getLineItems()
                .stream()
                .filter(item -> item.getItemId().equals(itemId))
                .findFirst()
                .map(lineItem -> lineItem.toBuilder().qty(replace?qty:lineItem.getQty() + qty).build());
    }

    private void updateItem(ShoppingCart shoppingCart, LineItem newLineItem) {
        List<LineItem> newItemList = Stream.concat(Stream.of(newLineItem), shoppingCart
                .getLineItems()
                .stream()
                .filter(item -> !item.getItemId().equals(newLineItem.getItemId()))).collect(Collectors.toList());

        ShoppingCart newShoppingCart = shoppingCart.toBuilder().lineItems(newItemList).build();

        shoppingCartRepo.update(newShoppingCart);
    }

    private ShoppingCart loadCart(String cartId, String userId) {
        Optional<ShoppingCart> shoppingCartOpt=shoppingCartRepo.getById(cartId);

        return shoppingCartOpt
                .filter(cart -> cart.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException(String.format("No Cart with id %s for user %s", cartId, userId)));
    }
}

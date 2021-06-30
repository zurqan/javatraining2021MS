package com.training.shoppingcart.adapter.repository;

import com.training.shoppingcart.model.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartRepo {
    void save(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getById(String cartId);

    void update(ShoppingCart newShoppingCart);
}

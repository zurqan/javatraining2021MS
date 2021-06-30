package com.training.shoppingcart.adapter.repository;

import com.training.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryShoppingCartRepo implements ShoppingCartRepo {
    Map<String,ShoppingCart> shoppingCarts = new HashMap<>();
    @Override
    public void save(ShoppingCart shoppingCart) {
        shoppingCarts.put(shoppingCart.getCartId(),shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getById(String cartId) {

        return Optional.ofNullable(shoppingCarts.get(cartId));
    }

    @Override
    public void update(ShoppingCart newShoppingCart) {
        shoppingCarts.put(newShoppingCart.getCartId(),newShoppingCart);

    }
}

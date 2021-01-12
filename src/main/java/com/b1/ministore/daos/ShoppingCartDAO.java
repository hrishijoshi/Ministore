package com.b1.ministore.daos;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.b1.ministore.entities.ShoppingCart;

@Service
public class ShoppingCartDAO {

  private Map<String, ShoppingCart> carts = new ConcurrentHashMap<>();

  public ShoppingCart createCart() {
    ShoppingCart cart = new ShoppingCart();
    cart.setId(UUID.randomUUID().toString());
    carts.put(cart.getId(), cart);
    return cart;
  }

  public ShoppingCart saveCart(ShoppingCart cart) {
    carts.put(cart.getId(), cart);
    return cart;
  }


  public ShoppingCart getCart(String cartId) {
    return carts.get(cartId);
  }
}

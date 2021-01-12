package com.b1.ministore.api;

import com.b1.ministore.entities.ShoppingCart;

public class ShoppingCartResponse {

  private String requestId;

  private ShoppingCart cart;

  public ShoppingCartResponse(String requestId, ShoppingCart cart) {
    this.requestId = requestId;
    this.cart = cart;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public ShoppingCart getCart() {
    return cart;
  }

  public void setCart(ShoppingCart cart) {
    this.cart = cart;
  }

}

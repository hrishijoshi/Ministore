package com.b1.ministore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.b1.ministore.api.ShoppingCartRequest;
import com.b1.ministore.api.ShoppingCartResponse;
import com.b1.ministore.services.ShoppingCartService;

@RestController
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService service;

  @PostMapping(path = "/cart", consumes = {"application/json"})
  public ShoppingCartResponse processShoppingCartRequest(@RequestBody ShoppingCartRequest request)
      throws Exception {
    if (request.getCartId() == null) {
      return new ShoppingCartResponse(request.getRequestId(),
          service.createCart(request.getProductId(), request.getItemsCount()));
    } else {
      return new ShoppingCartResponse(request.getProductId(),
          service.updateProductItemsCount(request.getCartId(), request.getProductId(),
              request.getItemsCount()));
    }
  }

}

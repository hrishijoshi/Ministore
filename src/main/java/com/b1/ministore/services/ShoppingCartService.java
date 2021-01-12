package com.b1.ministore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.b1.ministore.daos.ShoppingCartDAO;
import com.b1.ministore.entities.Product;
import com.b1.ministore.entities.ShoppingCart;

@Service
public class ShoppingCartService {

  @Autowired
  private ShoppingCartDAO cartDAO;

  @Autowired
  private ProductManagementService productService;

  public ShoppingCart createCart(String productId, int itemsCount) throws Exception {
    ShoppingCart cart = cartDAO.createCart();
    return updateProductItemsCount(cart.getId(), productId, itemsCount);
  }

  public ShoppingCart updateProductItemsCount(String cartId, String productId, int count) throws Exception {
    if(count < 0) {
      throw new Exception("Can not set items count to negative");
    }
    synchronized(productId) {
      Product product = productService.getProduct(productId);
      if(product == null) {
        throw new Exception ("Product does not exist");
      }
      synchronized(cartId) {
        ShoppingCart cart = cartDAO.getCart(cartId);
        if(cart  == null) {
          throw new Exception("Shopping cart does not exist");
        }
        if(count == 0) {
          cart.getItems().remove(productId);
        } else {
          cart.getItems().put(productId, count);
        }
        return cartDAO.saveCart(cart);
      }
    }

  }

}

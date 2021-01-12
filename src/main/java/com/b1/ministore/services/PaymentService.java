package com.b1.ministore.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.b1.ministore.api.CheckoutItem;
import com.b1.ministore.api.CheckoutResponse;
import com.b1.ministore.daos.ProductDAO;
import com.b1.ministore.daos.ShoppingCartDAO;
import com.b1.ministore.entities.BundleDeal;
import com.b1.ministore.entities.Deal;
import com.b1.ministore.entities.DiscountDeal;
import com.b1.ministore.entities.Product;
import com.b1.ministore.entities.ShoppingCart;

@Service
public class PaymentService {

  @Autowired
  private ShoppingCartDAO cartDAO;

  @Autowired
  private ProductDAO productDAO;


  public CheckoutResponse checkout(String cartId) {
    final CheckoutResponse response = new CheckoutResponse();
    synchronized (cartId) {
      ShoppingCart cart = cartDAO.getCart(cartId);
      Map<String, Integer> copy = new HashMap<String, Integer>();
      copy.putAll(cart.getItems());
      for (Map.Entry<String, Integer> e : copy.entrySet()) {
        Product p = productDAO.getProduct(e.getKey());
        if (e.getValue() == 0) {
          // this product has no quantity to be considered
          continue;
        }
        Deal deal = p.getDeal();
        if (deal == null) {
          // There is no deal for this product, just add the items into the checkout response with
          // their price
          addItemToResponse(p, e.getValue(), false, 0, response);
        } else if (deal instanceof BundleDeal) {
          Product primaryProduct = null;
          Product secondaryProduct = null;
          if (deal.getBaseProductId().equals(e.getKey())) {
            primaryProduct = p;
            secondaryProduct = productDAO.getProduct(((BundleDeal) deal).getSecondaryProductId());
          } else {
            secondaryProduct = p;
            primaryProduct = productDAO.getProduct(((BundleDeal) deal).getBaseProductId());
          }
          Integer primaryProductCount = copy.getOrDefault(primaryProduct.getId(), 0);
          Integer secondaryProductCount = copy.getOrDefault(secondaryProduct.getId(), 0);
          int setDealsCount = primaryProductCount - secondaryProductCount;
          if (setDealsCount >= 0) {
            addItemToResponse(primaryProduct, primaryProductCount, true, 0, response);
            addItemToResponse(secondaryProduct, primaryProductCount, true, deal.getDiscountPercentage(), response);
            copy.put(primaryProduct.getId(), setDealsCount);
            copy.put(secondaryProduct.getId(), 0);
          } else {
            addItemToResponse(primaryProduct, primaryProductCount, true, 0, response);
            addItemToResponse(secondaryProduct, primaryProductCount, true, deal.getDiscountPercentage(), response);
            addItemToResponse(secondaryProduct, secondaryProductCount - primaryProductCount, false, 0, response);
            copy.put(primaryProduct.getId(), 0);
            copy.put(secondaryProduct.getId(), 0);
          }
        } else if (deal instanceof DiscountDeal) {
          int itemsCount = Optional.ofNullable(e.getValue()).orElse(0);
          int discountSetCount = Math.floorDiv(itemsCount, ((DiscountDeal) deal).getDiscountSetCount());
          int itemsWithoutDiscount = itemsCount - (discountSetCount * ((DiscountDeal) deal).getDiscountSetCount());
          int discountedItemsCount = discountSetCount > 0 ? discountSetCount : 0;
          int itemsInSet = (discountSetCount * ((DiscountDeal) deal).getDiscountSetCount()) - discountSetCount;
          addItemToResponse(p, itemsInSet, true, 0, response);
          addItemToResponse(p, itemsWithoutDiscount, false, 0, response);
          addItemToResponse(p, discountedItemsCount, true, deal.getDiscountPercentage(), response);
        }
      }
      return response;
    }
  }

  private void addItemToResponse(Product product, int count, boolean applyDeal, int discountPercentage,
      CheckoutResponse response) {
    double price = product.getPrice() * (100 - discountPercentage) / 100;
    String dealId = applyDeal ? product.getDeal().getDealId() : null;
    for (int i = 0; i < count; i++) {
      response.setAmount(response.getAmount() + price);
      response.getItems().add(new CheckoutItem(product.getId(), price, dealId));
    }

  }
}

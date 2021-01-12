package com.b1.ministore.api;

public class CheckoutItem {

  private String productId;
  private double price;
  private String dealId;

  public CheckoutItem(String productId, double price, String dealId) {
    this.productId = productId;
    this.price = price;
    this.dealId = dealId;
  }

  public String getProductId() {
    return productId;
  }

  public double getPrice() {
    return price;
  }

  public String getDealId() {
    return dealId;
  }
}

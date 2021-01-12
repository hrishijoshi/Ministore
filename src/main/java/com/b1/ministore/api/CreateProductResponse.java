package com.b1.ministore.api;

import com.b1.ministore.entities.Product;

public class CreateProductResponse {

  private String requestId;
  private Product product;

  public CreateProductResponse(String requestId, Product product) {
    this.requestId = requestId;
    this.product = product;
  }

  public String getRequestId() {
    return requestId;
  }

  public Product getProduct() {
    return product;
  }
}

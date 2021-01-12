package com.b1.ministore.api;

import com.b1.ministore.entities.Product;

public class UpdateProductRequest {

  private String requestId;

  private Product product;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  
}

package com.b1.ministore.api;

import java.util.ArrayList;
import java.util.List;

public class CheckoutResponse {

  private String requestId;
  private List<CheckoutItem> items = new ArrayList<>();
  private double amount;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public List<CheckoutItem> getItems() {
    return items;
  }

  public void setItems(List<CheckoutItem> items) {
    this.items = items;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


}

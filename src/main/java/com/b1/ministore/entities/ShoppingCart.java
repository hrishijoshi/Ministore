package com.b1.ministore.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShoppingCart {
  private String id;
  private Map<String, Integer> items = new ConcurrentHashMap<>();

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Map<String, Integer> getItems() {
    return items;
  }
  public void setItems(Map<String, Integer> items) {
    this.items = items;
  }

}

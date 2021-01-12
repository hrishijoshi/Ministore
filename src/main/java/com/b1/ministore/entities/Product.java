package com.b1.ministore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {

  private String id;
  private Double price;
  private String description;
  private Deal deal;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @JsonIgnore
  public Deal getDeal() {
    return deal;
  }

  public void setDeal(Deal deal) {
    this.deal = deal;
  }


}

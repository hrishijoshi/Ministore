package com.b1.ministore.entities;

public abstract class Deal {

  private String dealId;

  private String baseProductId;

  private int discountPercentage;

  public String getDealId() {
    return dealId;
  }

  public void setDealId(String dealId) {
    this.dealId = dealId;
  }

  public String getBaseProductId() {
    return baseProductId;
  }

  public void setBaseProductId(String baseProductId) {
    this.baseProductId = baseProductId;
  }

  public int getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(int discountPercentage) {
    this.discountPercentage = discountPercentage;
  }

}

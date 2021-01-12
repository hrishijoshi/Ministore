package com.b1.ministore.entities;

public class BundleDeal extends Deal {

  private String secondaryProductId;

  public void setSecondaryProductId(String secondaryProductId) {
    this.secondaryProductId = secondaryProductId;
  }

  public String getSecondaryProductId() {
    return secondaryProductId;
  }

}

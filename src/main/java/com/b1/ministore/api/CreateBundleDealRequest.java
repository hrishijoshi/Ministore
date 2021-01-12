package com.b1.ministore.api;

public class CreateBundleDealRequest extends NewDealRequest {

  private String secondaryProductId;


  public String getSecondaryProductId() {
    return secondaryProductId;
  }

  public void setSecondaryProductId(String secondaryProductId) {
    this.secondaryProductId = secondaryProductId;
  }

  
}

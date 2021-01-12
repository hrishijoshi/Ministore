package com.b1.ministore.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(include = As.PROPERTY, property = "@type", use = Id.NAME)
@JsonSubTypes({@JsonSubTypes.Type(value = CreateBundleDealRequest.class, name = "bundle"),
    @JsonSubTypes.Type(value = CreateDiscountDealRequest.class, name = "discount")})
public abstract class NewDealRequest {

  private String requestId;

  private String baseProductId;

  private int discountPercentage;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
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
}

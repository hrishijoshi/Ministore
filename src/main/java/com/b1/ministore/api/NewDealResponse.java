package com.b1.ministore.api;

import com.b1.ministore.entities.Deal;

public class NewDealResponse {

  private String requestId;
  private Deal deal;

  public NewDealResponse(String requestId, Deal deal) {
    this.requestId = requestId;
    this.deal = deal;
  }

  public String getRequestId() {
    return requestId;
  }
  public Deal getDeal() {
    return deal;
  }

}

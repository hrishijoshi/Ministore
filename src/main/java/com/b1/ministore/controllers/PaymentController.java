package com.b1.ministore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.b1.ministore.api.CheckoutRequest;
import com.b1.ministore.api.CheckoutResponse;
import com.b1.ministore.services.PaymentService;

@RestController
public class PaymentController {

  @Autowired
  private PaymentService service;

  @PostMapping(path = "/checkout", consumes = {"application/json"})
  public CheckoutResponse checkout(@RequestBody CheckoutRequest request) {
    CheckoutResponse response = service.checkout(request.getCartId());
    response.setRequestId(request.getRequestId());
    return response;
  }
}

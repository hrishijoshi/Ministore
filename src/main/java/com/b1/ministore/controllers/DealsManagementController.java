package com.b1.ministore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.b1.ministore.api.NewDealRequest;
import com.b1.ministore.api.NewDealResponse;
import com.b1.ministore.api.RemoveDealRequest;
import com.b1.ministore.entities.Deal;
import com.b1.ministore.services.DealsManagementService;

@RestController()
public class DealsManagementController {

  @Autowired
  private DealsManagementService dealsManagementService;

  @GetMapping("/products/deals")
  public List<Deal> getAllDeals() {
    return  dealsManagementService.getAllDeals();
  }

  @PostMapping(path="/products/deals", consumes = {"application/json"})
  public NewDealResponse createNewDeal(@RequestBody NewDealRequest request) throws Exception {
    return new NewDealResponse(request.getRequestId(), dealsManagementService.createNewDeal(request));
  }

  @DeleteMapping(path="/products/deals", consumes = {"application/json"})
  public void removeDeal(@RequestBody RemoveDealRequest request) throws Exception {
    dealsManagementService.removeDeal(request.getDealId());
  }
}

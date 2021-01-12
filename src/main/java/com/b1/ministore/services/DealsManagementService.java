package com.b1.ministore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.b1.ministore.api.CreateBundleDealRequest;
import com.b1.ministore.api.CreateDiscountDealRequest;
import com.b1.ministore.api.NewDealRequest;
import com.b1.ministore.daos.DealDAO;
import com.b1.ministore.entities.BundleDeal;
import com.b1.ministore.entities.Deal;
import com.b1.ministore.entities.DiscountDeal;

@Service()
public class DealsManagementService {

  @Autowired
  private DealDAO dealDAO;

  @Autowired
  private ProductManagementService productManagementService;

  public synchronized Deal createNewDeal(NewDealRequest request) throws Exception {
    if (request instanceof CreateBundleDealRequest) {
      BundleDeal newDeal = new BundleDeal();
      newDeal.setBaseProductId(request.getBaseProductId());
      newDeal.setDiscountPercentage(request.getDiscountPercentage());
      newDeal.setSecondaryProductId(((CreateBundleDealRequest) request).getSecondaryProductId());
      dealDAO.createNewDeal(newDeal);
      productManagementService.linkProductAndDeal(request.getBaseProductId(), newDeal);
      productManagementService.linkProductAndDeal(
          ((CreateBundleDealRequest) request).getSecondaryProductId(), newDeal);
      return newDeal;
    } else if (request instanceof CreateDiscountDealRequest) {
      DiscountDeal newDeal = new DiscountDeal();
      newDeal.setBaseProductId(request.getBaseProductId());
      newDeal.setDiscountPercentage(request.getDiscountPercentage());
      newDeal.setDiscountSetCount(((CreateDiscountDealRequest) request).getDiscountSetCount());
      dealDAO.createNewDeal(newDeal);
      productManagementService.linkProductAndDeal(request.getBaseProductId(), newDeal);
      return newDeal;
    } else {
      throw new Exception("Unsupported request");
    }
  }

  public List<Deal> getAllDeals() {
    return dealDAO.getAllDeals();
  }

  public Deal getDeal(String dealId) {
    return dealDAO.getDeal(dealId);
  }

  public void removeDeal(String dealId) throws Exception {
    Deal deal = dealDAO.getDeal(dealId);
    if(deal == null) {
      return;
    }
    productManagementService.delinkProductAndDeal(deal.getBaseProductId());
    if(deal instanceof BundleDeal) {
      productManagementService.delinkProductAndDeal(((BundleDeal) deal).getSecondaryProductId());
    }
    dealDAO.deleteDeal(dealId);
  }

}

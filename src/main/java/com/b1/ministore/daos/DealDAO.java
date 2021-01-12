package com.b1.ministore.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.b1.ministore.entities.Deal;

@Service
public class DealDAO {

  private Map<String, Deal> deals = new ConcurrentHashMap<>();

  public Deal createNewDeal(Deal newDeal) {
    newDeal.setDealId(UUID.randomUUID().toString());
    deals.put(newDeal.getDealId(), newDeal);
    return newDeal;
  }

  public Deal getDeal(String dealId) {
    return deals.get(dealId);
  }

  public List<Deal> getAllDeals() {
    return new ArrayList<>(deals.values());
  }

  public void deleteDeal(String dealId) {
    deals.remove(dealId);
  }
}

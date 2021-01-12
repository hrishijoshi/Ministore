package com.b1.ministore.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.b1.ministore.api.CreateDiscountDealRequest;
import com.b1.ministore.daos.DealDAO;

@SpringBootTest
public class DealsManagementServiceTest {

  @MockBean
  private DealDAO dealDAO;

  @MockBean
  private ProductManagementService productManagementService;

  @Autowired
  private DealsManagementService underTest;

  @Test
  public void createDeal() {
    try {
      underTest.createNewDeal(new CreateDiscountDealRequest());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  @Test
  public void remove() {
    try {
      underTest.createNewDeal(new CreateDiscountDealRequest());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}

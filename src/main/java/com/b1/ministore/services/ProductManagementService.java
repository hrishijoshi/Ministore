package com.b1.ministore.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.b1.ministore.daos.ProductDAO;
import com.b1.ministore.entities.Deal;
import com.b1.ministore.entities.Product;

@Service
public class ProductManagementService {

  @Autowired
  private ProductDAO productDAO;

  public List<Product> getAllProducts() {
    return productDAO.getAllProducts();
  }

  public Product getProduct(String productId) {
    return productDAO.getProduct(productId);
  }
  public Product createProduct(double price, String description) {
    Product product = new Product();
    product.setDescription(description);
    product.setPrice(price);
    return productDAO.createNewProduct(product);
  }

  public Product updateProduct(Product updatedProduct) throws Exception {
    synchronized(updatedProduct.getId()) {
      Product existingProduct = productDAO.getProduct(updatedProduct.getId());
      if(updatedProduct.getDescription() != null) {
        existingProduct.setDescription(updatedProduct.getDescription());
      }
      if(updatedProduct.getPrice() != null) {
        existingProduct.setPrice(updatedProduct.getPrice());
      }
      return productDAO.updateProduct(existingProduct);
    }
  }

  public void linkProductAndDeal(String productId, Deal deal) throws Exception {
    synchronized(productId) {
      Product product = productDAO.getProduct(productId);
      product.setDeal(deal);
      productDAO.updateProduct(product);
    }
  }

  public void delinkProductAndDeal(String productId) throws Exception {
    synchronized(productId) {
      Product product = productDAO.getProduct(productId);
      product.setDeal(null);
      productDAO.updateProduct(product);
    }
  }

  public void deleteProduct(String productId) {
    productDAO.removeProduct(productId);
  }
}

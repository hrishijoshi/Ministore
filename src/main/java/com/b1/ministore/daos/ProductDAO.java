package com.b1.ministore.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.b1.ministore.entities.Product;

@Service
public class ProductDAO {

  private Map<String, Product> products = new ConcurrentHashMap<>();

  public Product getProduct(String productId) {
    return products.get(productId);
  }

  public List<Product> getAllProducts() {
    return new ArrayList<>(products.values());
  }

  public Product createNewProduct(Product newProduct) {
    newProduct.setId(UUID.randomUUID().toString());
    products.put(newProduct.getId(), newProduct);
    return newProduct;
  }

  public Product updateProduct(Product updatedProduct) throws Exception {
    Product existingProduct = products.get(updatedProduct.getId());
    if (existingProduct == null) {
      throw new Exception("Product not found");
    }
    products.put(updatedProduct.getId(), existingProduct);
    return existingProduct;
  }

  public void removeProduct(String productId) {
    products.remove(productId);
  }

}

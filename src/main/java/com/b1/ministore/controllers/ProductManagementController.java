package com.b1.ministore.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.b1.ministore.api.CreateProductRequest;
import com.b1.ministore.api.CreateProductResponse;
import com.b1.ministore.api.DeleteProductRequest;
import com.b1.ministore.api.UpdateProductRequest;
import com.b1.ministore.api.UpdateProductResponse;
import com.b1.ministore.entities.Product;
import com.b1.ministore.services.ProductManagementService;

@RestController()
public class ProductManagementController {
  @Autowired()
  private ProductManagementService productManagementService;

  @GetMapping(path = "/products")
  public List<Product> getProducts() {
    return productManagementService.getAllProducts();
  }

  @PostMapping(path = "/products", consumes = {"application/json"})
  public CreateProductResponse createProduct(@RequestBody CreateProductRequest newProductRequest) {
    return new CreateProductResponse(newProductRequest.getRequestId(), productManagementService
        .createProduct(newProductRequest.getPrice(), newProductRequest.getDescription()));
  }

  @PutMapping(path = "/products", consumes = {"application/json"})
  public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest updateProductRequest)
      throws Exception {
    return new UpdateProductResponse(updateProductRequest.getRequestId(),
        productManagementService.updateProduct(updateProductRequest.getProduct()));
  }

  @DeleteMapping(path = "/products", consumes = {"application/json"})
  public void deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
    productManagementService.deleteProduct(deleteProductRequest.getProductId());
  }
}

package com.than.spring_shop.service.product;

import com.than.spring_shop.entity.Product;
import com.than.spring_shop.exception.NotFoundException;
import com.than.spring_shop.repository.ProductRepository;
import java.util.List;

public class ProductServiceImpl implements ProductService {
  private ProductRepository productRepository;

  @Override
  public Product addProduct(Product product) {
    return null;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> getProductsByCategory(String category) {
    return List.of();
  }

  @Override
  public List<Product> getProductsByBrand(String brand) {
    return List.of();
  }

  @Override
  public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
    return List.of();
  }

  @Override
  public List<Product> getProductsByName(String name) {
    return List.of();
  }

  @Override
  public List<Product> getProductsByBrandAndName(String brand, String name) {
    return List.of();
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("product not found"));
  }

  @Override
  public Product updateProduct(Long id, Product product) {
    return null;
  }

  @Override
  public void deleteProduct(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("product not found"));

    productRepository.delete(product);
  }

  @Override
  public Long countProductsByBrandAndName(String brand, String name) {
    return 0L;
  }
}

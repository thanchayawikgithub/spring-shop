package com.than.spring_shop.service.product;

import com.than.spring_shop.entity.Product;
import com.than.spring_shop.exception.NotFoundException;
import com.than.spring_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
  private final ProductRepository productRepository;

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
    return productRepository.findByCategoryName(category);
  }

  @Override
  public List<Product> getProductsByBrand(String brand) {
    return productRepository.findByBrand(brand);
  }

  @Override
  public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
    return productRepository.findByCategoryNameAndBrand(category,brand);
  }

  @Override
  public List<Product> getProductsByName(String name) {
    return productRepository.findByName(name);
  }

  @Override
  public List<Product> getProductsByBrandAndName(String brand, String name) {
    return productRepository.findByBrandAndName(brand,name);
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
    return productRepository.countByBrandAndName(brand,name);
  }
}

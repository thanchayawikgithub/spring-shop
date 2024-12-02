package com.than.spring_shop.service.product;

import com.than.spring_shop.entity.Category;
import com.than.spring_shop.entity.Product;
import com.than.spring_shop.exception.NotFoundException;
import com.than.spring_shop.repository.CategoryRepository;
import com.than.spring_shop.repository.ProductRepository;
import com.than.spring_shop.request.product.AddProductRequest;
import com.than.spring_shop.request.product.UpdateProductRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Override
  @Transactional
  public Product addProduct(AddProductRequest request) {
    String categoryName = request.getCategory().getName();
    Category category =
        categoryRepository
            .findByName(categoryName)
            .orElseGet(() -> categoryRepository.save(new Category(categoryName)));

    Product product =
        new Product(
            request.getName(),
            request.getDescription(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            category);

    return productRepository.save(product);
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
    return productRepository.findByCategoryNameAndBrand(category, brand);
  }

  @Override
  public List<Product> getProductsByName(String name) {
    return productRepository.findByName(name);
  }

  @Override
  public List<Product> getProductsByBrandAndName(String brand, String name) {
    return productRepository.findByBrandAndName(brand, name);
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("product not found"));
  }

  @Override
  public Product updateProduct(Long id, UpdateProductRequest request) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("product not found"));

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setBrand(request.getBrand());
    product.setPrice(request.getPrice());
    product.setInventory(request.getInventory());

    Category category =
        categoryRepository
            .findByName(request.getCategory().getName())
            .orElseThrow(() -> new NotFoundException("category not found"));
    product.setCategory(category);

    return productRepository.save(product);
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
    return productRepository.countByBrandAndName(brand, name);
  }
}

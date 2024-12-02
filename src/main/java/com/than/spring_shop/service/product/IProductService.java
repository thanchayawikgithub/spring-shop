package com.than.spring_shop.service.product;

import com.than.spring_shop.entity.Product;
import com.than.spring_shop.request.product.AddProductRequest;
import com.than.spring_shop.request.product.UpdateProductRequest;
import java.util.List;

public interface IProductService {
  Product addProduct(AddProductRequest request);

  List<Product> getAllProducts();

  List<Product> getProductsByCategory(String category);

  List<Product> getProductsByBrand(String brand);

  List<Product> getProductsByCategoryAndBrand(String category, String brand);

  List<Product> getProductsByName(String name);

  List<Product> getProductsByBrandAndName(String brand, String name);

  Product getProductById(Long id);

  Product updateProduct(Long id, UpdateProductRequest request);

  void deleteProduct(Long id);

  Long countProductsByBrandAndName(String brand, String name);
}

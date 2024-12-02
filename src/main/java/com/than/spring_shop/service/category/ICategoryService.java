package com.than.spring_shop.service.category;

import com.than.spring_shop.entity.Category;
import com.than.spring_shop.request.category.AddCategoryRequest;
import com.than.spring_shop.request.category.UpdateCategoryRequest;
import java.util.List;

public interface ICategoryService {
  Category getCategoryById(Long id);

  Category getCategoryByName(String name);

  List<Category> getAllCategories();

  Category addCategory(AddCategoryRequest request);

  Category updateCategory(Long id, UpdateCategoryRequest request);

  void deleteCategory(Long id);
}

package com.than.spring_shop.service.category;

import com.than.spring_shop.entity.Category;
import com.than.spring_shop.exception.AlreadyExistsException;
import com.than.spring_shop.exception.ResourceNotFoundException;
import com.than.spring_shop.repository.CategoryRepository;
import com.than.spring_shop.request.category.AddCategoryRequest;
import com.than.spring_shop.request.category.UpdateCategoryRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public Category getCategoryById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("category not found"));
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository
        .findByName(name)
        .orElseThrow(() -> new ResourceNotFoundException("category not found"));
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category addCategory(AddCategoryRequest request) {
    String categoryName = request.getName();
    if (categoryRepository.existsByName(categoryName)) {
      throw new AlreadyExistsException("category name " + categoryName + " already exists");
    }
    return categoryRepository.save(new Category(categoryName));
  }

  @Override
  public Category updateCategory(Long id, UpdateCategoryRequest request) {
    Category category = getCategoryById(id);
    category.setName(request.getName());
    return categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(Long id) {
    Category category = getCategoryById(id);

    categoryRepository.delete(category);
  }
}

package com.than.spring_shop.controller;

import com.than.spring_shop.entity.Category;
import com.than.spring_shop.exception.AlreadyExistsException;
import com.than.spring_shop.request.category.AddCategoryRequest;
import com.than.spring_shop.request.category.UpdateCategoryRequest;
import com.than.spring_shop.response.ApiResponse;
import com.than.spring_shop.service.category.ICategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {
  private final ICategoryService categoryService;

  @PostMapping
  public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategoryRequest request) {
    try {
      Category category = categoryService.addCategory(request);
      return ResponseEntity.ok(new ApiResponse("Success", category));
    } catch (AlreadyExistsException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
    }
  }

  @GetMapping
  public ResponseEntity<ApiResponse> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    return ResponseEntity.ok(new ApiResponse("Success", categories));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
    Category category = categoryService.getCategoryById(id);
    return ResponseEntity.ok(new ApiResponse("Success", category));
  }

  @GetMapping("/name/{id}")
  public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
    Category category = categoryService.getCategoryByName(name);
    return ResponseEntity.ok(new ApiResponse("Success", category));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateCategory(
      @PathVariable Long id, @RequestBody UpdateCategoryRequest request) {
    Category category = categoryService.updateCategory(id, request);
    return ResponseEntity.ok(new ApiResponse("Success", category));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.ok(new ApiResponse("Success", null));
  }
}

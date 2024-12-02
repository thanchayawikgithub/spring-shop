package com.than.spring_shop.request.product;

import com.than.spring_shop.entity.Category;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpdateProductRequest {
  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private int inventory;
  private Category category;
}

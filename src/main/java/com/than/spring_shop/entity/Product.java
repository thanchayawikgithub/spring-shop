package com.than.spring_shop.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String brand;
  private BigDecimal price;
  private int inventory;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id")
  private Category category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Image> images;

  public Product(
      String name,
      String description,
      String brand,
      BigDecimal price,
      int inventory,
      Category category) {
    this.name = name;
    this.description = description;
    this.brand = brand;
    this.price = price;
    this.inventory = inventory;
    this.category = category;
  }
}

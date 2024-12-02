package com.than.spring_shop.entity;

import jakarta.persistence.*;
import java.sql.Blob;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fileName;
  private String fileType;

  @Lob private Blob image;
  private String downloadUrl;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
}

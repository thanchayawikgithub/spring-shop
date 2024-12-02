package com.than.spring_shop.service.image;

import com.than.spring_shop.dto.ImageDto;
import com.than.spring_shop.entity.Image;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
  Image getImageById(Long id);

  void deleteImage(Long id);

  List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

  void updateImage(Long id, MultipartFile file);
}

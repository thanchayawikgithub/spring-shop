package com.than.spring_shop.service.image;

import com.than.spring_shop.dto.ImageDto;
import com.than.spring_shop.entity.Image;
import com.than.spring_shop.entity.Product;
import com.than.spring_shop.exception.ResourceNotFoundException;
import com.than.spring_shop.repository.ImageRepository;
import com.than.spring_shop.service.product.IProductService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
  private final ImageRepository imageRepository;
  private final IProductService productService;

  @Override
  public Image getImageById(Long id) {
    return imageRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("image not found"));
  }

  @Override
  public void deleteImage(Long id) {
    imageRepository.delete(getImageById(id));
  }

  @Override
  public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
    Product product = productService.getProductById(productId);
    List<ImageDto> savedImageDtos = new ArrayList<>();

    for (MultipartFile file : files) {
      try {
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setImage(new SerialBlob(file.getBytes()));
        image.setProduct(product);

        Image savedImage = imageRepository.save(image);
        savedImage.setDownloadUrl("/api/v1/images/image/download/" + savedImage.getId());
        imageRepository.save(savedImage);

        ImageDto imageDto = new ImageDto();
        imageDto.setId(savedImage.getId());
        imageDto.setName(savedImage.getFileName());
        imageDto.setDownloadUrl(savedImage.getDownloadUrl());
        savedImageDtos.add(imageDto);
      } catch (IOException | SQLException e) {
        throw new RuntimeException(e.getMessage());
      }
    }
    return savedImageDtos;
  }

  @Override
  public void updateImage(Long id, MultipartFile file) {
    Image image = getImageById(id);

    try {
      image.setFileType(file.getContentType());
      image.setFileName(file.getOriginalFilename());
      image.setImage(new SerialBlob(file.getBytes()));
      imageRepository.save(image);
    } catch (IOException | SQLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}

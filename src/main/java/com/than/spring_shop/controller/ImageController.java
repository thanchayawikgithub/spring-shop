package com.than.spring_shop.controller;

import com.than.spring_shop.dto.ImageDto;
import com.than.spring_shop.entity.Image;
import com.than.spring_shop.exception.ResourceNotFoundException;
import com.than.spring_shop.response.ApiResponse;
import com.than.spring_shop.service.image.IImageService;
import java.sql.SQLException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {
  private final IImageService imageService;

  @PostMapping("/upload")
  public ResponseEntity<ApiResponse> saveImages(
      @RequestParam List<MultipartFile> files, @RequestParam Long productId) {
    try {
      List<ImageDto> imageDtos = imageService.saveImages(files, productId);
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(new ApiResponse("Upload success", imageDtos));
    } catch (Exception e) {
      return ResponseEntity.internalServerError()
          .body(new ApiResponse("Upload failed", e.getMessage()));
    }
  }

  @GetMapping("/image/download/{id}")
  public ResponseEntity<Resource> downloadImage(@PathVariable Long id) throws SQLException {
    Image image = imageService.getImageById(id);
    ByteArrayResource resource =
        new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(image.getFileType()))
        .header(
            HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
        .body(resource);
  }

  @PutMapping("/image/{id}/update")
  public ResponseEntity<ApiResponse> updateImage(
      @PathVariable Long id, @RequestBody MultipartFile file) {
    try {
      imageService.updateImage(id, file);
      return ResponseEntity.ok(new ApiResponse("Update success", null));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse(e.getMessage(), null));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new ApiResponse("Update failed", null));
    }
  }

  @DeleteMapping("/image/{id}/delete")
  public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long id) {
    try {
      imageService.deleteImage(id);
      return ResponseEntity.ok(new ApiResponse("Update success", null));
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ApiResponse(e.getMessage(), null));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(new ApiResponse("Update failed", null));
    }
  }
}

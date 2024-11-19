package com.imageupload.imageupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imageupload.imageupload.response.ImageUploadResponse;
import com.imageupload.imageupload.service.CloudinaryImageService;

@RestController
@RequestMapping("/cloudinary/image")
public class CloudinaryImageController {

    @Autowired
    private CloudinaryImageService imageDataService;

    // Upload image to Cloud
    @PostMapping
    public ResponseEntity<ImageUploadResponse> c_UploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        ImageUploadResponse response = imageDataService.c_UploadImage(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    // Get Img Url
    @GetMapping("/{publicId}")
    public ResponseEntity<String> c_GetImageUrl(@PathVariable("publicId") String publicId) throws IOException {
        String imageUrl = imageDataService.c_GetImageUrl(publicId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(imageUrl);
    }

    // Delete Img Url
    @DeleteMapping("/{publicId}")
    public ResponseEntity<String> c_DeleteImage(@PathVariable("publicId") String publicId) throws IOException {
        imageDataService.c_DeleteImage(publicId);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Image deleted successfully");
    }

}

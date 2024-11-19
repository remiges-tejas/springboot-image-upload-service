package com.imageupload.imageupload.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imageupload.imageupload.service.ImageDataService;

@RestController
@RequestMapping("/api/uploads")
public class ImageController {

    @Autowired
    private ImageDataService imageService;

    // Directory where images will be uploaded
    @Value("${file.upload-dir}")
    private String uploadDir;

    // Image Upload Endpoint
    @PostMapping("/images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file and get the path
            String filePath = saveImage(file);
            return ResponseEntity.ok("Image uploaded successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        // Define the directory for file upload
        Path uploadPath = Paths.get(uploadDir);

        // Ensure the directory exists
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save the file with its original name
        String fileName =  "_uploaded_s"+file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    // Endpoint to Retrieve an Image by Filename
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            // Get the file path
            Path filePath = Paths.get(uploadDir).resolve(filename);

            // Convert it to a Resource object
            UrlResource resource = new UrlResource(filePath.toUri());

            // Check if resource exists and is readable
            if (resource.exists() && resource.isReadable()) {
                // Return the image as a response
                return ResponseEntity.ok()
                        .contentType(determineMediaType(filename)) // Dynamically determine media type
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Utility to determine file media type
    private MediaType determineMediaType(String filename) {
        if (filename.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (filename.endsWith(".jpeg") || filename.endsWith(".jpg")) {
            return MediaType.IMAGE_JPEG;
        } else {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}

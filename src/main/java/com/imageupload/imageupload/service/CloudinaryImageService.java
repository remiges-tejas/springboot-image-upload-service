package com.imageupload.imageupload.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.imageupload.imageupload.response.ImageUploadResponse;

@Service
public class CloudinaryImageService {

    @Autowired
    private Cloudinary cloudinary;

    // Uplaod image to Cloundinary
    public ImageUploadResponse c_UploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();
        String publicId = uploadResult.get("public_id").toString();

        return new ImageUploadResponse("Image uploaded successfully", url);
    }

    // Get Image Url From Cloudinary
    public String c_GetImageUrl(String publicId) throws IOException {
        return cloudinary.url().generate(publicId);
    }

    // Delete the Image From Cloudinary
    public void c_DeleteImage(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }

}

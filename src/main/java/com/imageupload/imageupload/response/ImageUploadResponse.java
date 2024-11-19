package com.imageupload.imageupload.response;

import lombok.Data;

@Data
public class ImageUploadResponse {

    // Instane Varible
    private String message;
    private String fileName;

    // Constructor
    public ImageUploadResponse(String message, String fileName) {
        this.message = message;
        this.fileName = fileName;
    }

}

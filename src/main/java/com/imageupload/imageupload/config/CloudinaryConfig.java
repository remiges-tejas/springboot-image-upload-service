package com.imageupload.imageupload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    // cloudinary.cloud_name=domibl8vf
    // cloudinary.api_key=834141862235618
    // cloudinary.api_secret=xxsdsd3HTw2

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "domibl8vf",
                "api_key", "834141862235618",
                "api_secret", "6dbRwp0a75yBfY8wFCPtCdSHL6M"));
    }

}
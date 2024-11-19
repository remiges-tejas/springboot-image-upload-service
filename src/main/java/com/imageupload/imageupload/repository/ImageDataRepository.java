package com.imageupload.imageupload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imageupload.imageupload.enitites.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name);

}
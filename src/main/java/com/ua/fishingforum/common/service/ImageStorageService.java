package com.ua.fishingforum.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String uploadImage(MultipartFile file);

    String getImage(String imageUrl);
}

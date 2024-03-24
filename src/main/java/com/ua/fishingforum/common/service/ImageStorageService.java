package com.ua.fishingforum.common.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    String uploadImage(MultipartFile file);

    byte[] getImage(String imageUrl);
}

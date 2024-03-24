package com.ua.fishingforum.user.posts.image.service;

import com.ua.fishingforum.user.posts.image.model.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    void savePhoto(Photo photo);
    Photo uploadPhoto(MultipartFile multipartFile);
}

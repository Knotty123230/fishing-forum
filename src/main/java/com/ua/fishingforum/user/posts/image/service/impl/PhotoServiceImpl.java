package com.ua.fishingforum.user.posts.image.service.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.image.model.Photo;
import com.ua.fishingforum.user.posts.image.repository.PhotoRepository;
import com.ua.fishingforum.user.posts.image.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final ImageStorageService imageStorageService;

    @Override
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public Photo uploadPhoto(MultipartFile multipartFile) {
        String imageUrl = imageStorageService.uploadImage(multipartFile);
        Photo photo = new Photo();
        photo.setImageUrl(imageUrl);
        return photoRepository.save(photo);
    }
}

package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PostToPostWithImageResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PhotoResponse;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostToPostWithImageResponseMapperImpl implements PostToPostWithImageResponseMapper {
    private final ImageStorageService imageStorageService;

    @Override
    public PostResponse map(Post source) {
        return new PostResponse(source.getName(),
                source.getDescription(),
                source.getPhotos().stream()
                        .map(photo -> new PhotoResponse(photo.getImageUrl(), photo.getCreatedAt(), imageStorageService.getImage(photo.getImageUrl())))
                        .toList(),
                source.getCreatedTimestamp());
    }
}

package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PhotoResponse;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostToPostResponseMapperImpl implements PostToPostResponseMapper {
    private final ImageStorageService imageStorageService;
    @Override
    public PostResponse map(Post post) {
        return new PostResponse(post.getName(),
                post.getDescription(),
                post.getPhotos()
                        .stream()
                        .map(photo -> new PhotoResponse(photo.getImageUrl(), photo.getCreatedAt(), imageStorageService.getImage(photo.getImageUrl())))
                        .toList(),
                post.getCreatedTimestamp());
    }
}

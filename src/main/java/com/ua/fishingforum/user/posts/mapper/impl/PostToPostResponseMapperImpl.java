package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PostToPostResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PhotoResponse;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostToPostResponseMapperImpl implements PostToPostResponseMapper {
    private final ImageStorageService imageStorageService;

    @Override
    public PostResponse map(Post post) {
        List<PhotoResponse> list = new LinkedList<>();
        if (post.getPhotos() != null) {
            list = post.getPhotos()
                    .stream()
                    .filter(it -> it.getImageUrl() != null)
                    .map(photo -> new PhotoResponse(imageStorageService.getImage(photo.getImageUrl()), photo.getCreatedAt()))
                    .toList();
        }
        return new PostResponse(post.getName(),
                post.getDescription(),
                list,
                post.getUserProfile(),
                post.getCreatedTimestamp());
    }
}

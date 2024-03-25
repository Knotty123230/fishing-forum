package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.common.service.ImageStorageService;
import com.ua.fishingforum.user.posts.mapper.PagePostsToAllPostsResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.AllPostsResponse;
import com.ua.fishingforum.user.posts.web.dto.PhotoResponse;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagePostsToAllPostsResponseMapperImpl implements PagePostsToAllPostsResponseMapper {
    private final ImageStorageService imageStorageService;

    @Override
    public AllPostsResponse map(Page<Post> source) {
        return new AllPostsResponse(
                source.isFirst(),
                source.isLast(),
                source.getTotalPages(),
                source.stream().map(
                        post -> new PostResponse(post.getName(),
                                post.getDescription(),
                                post.getPhotos()
                                        .stream()
                                        .map(photo -> new PhotoResponse(photo.getImageUrl(), photo.getCreatedAt(), imageStorageService.getImage(photo.getImageUrl())))
                                        .toList(),
                                post.getCreatedTimestamp()

                        )).toList()
        );
    }
}

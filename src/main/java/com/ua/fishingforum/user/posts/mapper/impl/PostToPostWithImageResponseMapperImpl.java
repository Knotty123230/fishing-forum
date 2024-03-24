package com.ua.fishingforum.user.posts.mapper.impl;

import com.ua.fishingforum.user.posts.mapper.PostToPostWithImageResponseMapper;
import com.ua.fishingforum.user.posts.model.Post;
import com.ua.fishingforum.user.posts.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostToPostWithImageResponseMapperImpl implements PostToPostWithImageResponseMapper {
    @Override
    public PostResponse map(Post source) {
        return new PostResponse(source.getName(), source.getDescription(), source.getPhotos(), source.getCreatedTimestamp());
    }
}
